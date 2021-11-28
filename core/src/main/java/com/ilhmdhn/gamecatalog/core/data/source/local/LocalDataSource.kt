package com.ilhmdhn.gamecatalog.core.data.source.local

import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameDetailEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameMovieEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameScreenshotEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getListGame(search: String): Flow<List<GameListEntity>>{
        if (search == ""){
            return gameDao.getGameList()
        } else{
            return gameDao.getSearchGame(search)
        }
    }

    suspend fun insertGameList(gameList: List<GameListEntity>) = gameDao.insertGameList(gameList)

    suspend fun deleteGameList() = gameDao.deleteGameList()

    fun getDetailGame(id: Int) = gameDao.getDetailGame(id)

    suspend fun insertGameDetail(detailGame: GameDetailEntity) = gameDao.insertGameDetail(detailGame)

    suspend fun deleteGameDetail() = gameDao.deleteGameDetail()

    fun getGameScreenshot(gameId: Int): Flow<List<GameScreenshotEntity>> = gameDao.getScreenshot(gameId)

    suspend fun insertScreenshot(ssData: List<GameScreenshotEntity>) = gameDao.insertScreenshot(ssData)

    suspend fun deleteScreenshot(gameId: Int) = gameDao.deleteScreenshot(gameId)

    fun getMovie(gameId: Int): Flow<List<GameMovieEntity>> = gameDao.getMovie(gameId)

    suspend fun insertMovie(movieData: List<GameMovieEntity>) = gameDao.insertMovie(movieData)

    suspend fun deleteMovie(gameId: Int) = gameDao.deleteMovie(gameId)
}