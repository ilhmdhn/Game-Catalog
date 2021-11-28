package com.ilhmdhn.gamecatalog.core.data

import GameListResult
import com.ilhmdhn.gamecatalog.core.data.source.local.LocalDataSource
import com.ilhmdhn.gamecatalog.core.data.source.remote.RemoteDataSource
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiResponse
import com.ilhmdhn.gamecatalog.core.data.source.remote.response.GameDetailResponse
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import com.ilhmdhn.gamecatalog.core.domain.repository.IGameRepository
import com.ilhmdhn.gamecatalog.core.utils.AppExecutors
import com.ilhmdhn.gamecatalog.core.utils.DataMapper
import com.ilhmdhn.imageslider.model.ResultsImageItem
import com.ilhmdhn.imageslider.model.ResultsItemMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getListGame(search: String, reload: Boolean): Flow<Resource<List<GameListModel>>> =
        object : NetworkBoundResource<List<GameListModel>, List<GameListResult>>(){
            override fun loadFromDB(): Flow<List<GameListModel>> {
                return localDataSource.getListGame(search).map {
                    DataMapper.mapListGameEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GameListModel>?): Boolean {
                return data.isNullOrEmpty() || reload
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameListResult>>> {
                return remoteDataSource.getListGame(search)
            }

            override suspend fun saveCallResult(data: List<GameListResult>) {
                if (reload){
                    localDataSource.deleteGameList()
                    localDataSource.deleteGameDetail()
                }
                val gameList = DataMapper.mapListGameResponseToEntity(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getDetailGame(id: Int, reload: Boolean): Flow<Resource<GameDetailModel>> =
        object: NetworkBoundResource<GameDetailModel, GameDetailResponse>(){
            override fun loadFromDB(): Flow<GameDetailModel> {
                return localDataSource.getDetailGame(id).map { detailGame ->
                    DataMapper.mapDetailGameEntityToDomain(detailGame)
                }
            }

            override fun shouldFetch(data: GameDetailModel?): Boolean {
                return data?.backgroundImage== null || reload
            }

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> {
                return remoteDataSource.getDetailGame(id)
            }

            override suspend fun saveCallResult(data: GameDetailResponse) {
                val detailGame = DataMapper.mapDetailGameResponseToEntity(data)
                localDataSource.insertGameDetail(detailGame)
            }
        }.asFlow()

    override fun getScreenshotGame(gameId: Int, reload: Boolean): Flow<Resource<List<GameScreenshotModel>>> =
        object: NetworkBoundResource<List<GameScreenshotModel>, List<ResultsImageItem>>(){
            override fun loadFromDB(): Flow<List<GameScreenshotModel>> {
                return localDataSource.getGameScreenshot(gameId).map{ssGame ->
                    DataMapper.mapScreenshotGameEntityToDomain(ssGame)
                }
            }

            override fun shouldFetch(data: List<GameScreenshotModel>?): Boolean {
                return data.isNullOrEmpty() || reload
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsImageItem>>> {
                return remoteDataSource.getScreenshotGame(gameId)
            }

            override suspend fun saveCallResult(data: List<ResultsImageItem>) {
                val ssGame = DataMapper.mapScreenshotGameResponseToEntity(gameId, data)
                localDataSource.deleteScreenshot(gameId)
                localDataSource.insertScreenshot(ssGame)
            }

        }.asFlow()

    override fun getMovieGame(gameId: Int, reload: Boolean): Flow<Resource<List<GameMovieModel>>> =
        object: NetworkBoundResource<List<GameMovieModel>, List<ResultsItemMovie>>(){
            override fun loadFromDB(): Flow<List<GameMovieModel>> {
                return localDataSource.getMovie(gameId).map{movieGame ->
                    DataMapper.mapMovieGameEntityToDomain(movieGame)
                }
            }

            override fun shouldFetch(data: List<GameMovieModel>?): Boolean {
                return data.isNullOrEmpty() || reload
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemMovie>>> {
                return remoteDataSource.getMovie(gameId)
            }

            override suspend fun saveCallResult(data: List<ResultsItemMovie>) {
                val movieGame = DataMapper.mapMovieGameResponseToEntity(gameId, data)
                localDataSource.deleteMovie(gameId)
                localDataSource.insertMovie(movieGame)
            }

        }.asFlow()
}