package com.ilhmdhn.gamecatalog.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM gamelistentities")
    fun getGameList(): Flow<List<GameListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(gameList: List<GameListEntity>)
}