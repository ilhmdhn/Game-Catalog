package com.ilhmdhn.gamecatalog.core.data

import GameListResult
import com.ilhmdhn.gamecatalog.core.data.source.local.LocalDataSource
import com.ilhmdhn.gamecatalog.core.data.source.remote.RemoteDataSource
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiResponse
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import com.ilhmdhn.gamecatalog.core.domain.repository.IGameRepository
import com.ilhmdhn.gamecatalog.core.utils.AppExecutors
import com.ilhmdhn.gamecatalog.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {
    override fun getListGame(): Flow<Resource<List<GameListModel>>> =
        object : NetworkBoundResource<List<GameListModel>, List<GameListResult>>(){
            override fun loadFromDB(): Flow<List<GameListModel>> {
                return localDataSource.getListGame().map {
                    DataMapper.mapListGameEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GameListModel>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GameListResult>>> {
                return remoteDataSource.getListGame()
            }

            override suspend fun saveCallResult(data: List<GameListResult>) {
                val gameList = DataMapper.mapListGameResponseToEntities(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()
}