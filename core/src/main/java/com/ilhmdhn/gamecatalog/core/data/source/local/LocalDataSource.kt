package com.ilhmdhn.gamecatalog.core.data.source.local

import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    fun getListGame(): Flow<List<GameListEntity>> = gameDao.getGameList()

    suspend fun insertGameList(gameList: List<GameListEntity>) = gameDao.insertGameList(gameList)
}