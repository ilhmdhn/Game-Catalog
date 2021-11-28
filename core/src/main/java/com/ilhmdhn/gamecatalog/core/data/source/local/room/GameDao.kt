package com.ilhmdhn.gamecatalog.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameDetailEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameMovieEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameScreenshotEntity
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.JdkConstants

@Dao
interface GameDao {
    @Query("SELECT * FROM gamelistentities")
    fun getGameList(): Flow<List<GameListEntity>>

    @Query("SELECT * FROM gamelistentities WHERE name LIKE '%'||:search||'%'")
    fun getSearchGame(search: String): Flow<List<GameListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(gameList: List<GameListEntity>)

    @Query("DELETE FROM gamelistentities")
    suspend fun deleteGameList()

    @Query("SELECT * FROM gamedetailentities WHERE id = :id")
    fun getDetailGame(id: Int): Flow<GameDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameDetail(detailGame: GameDetailEntity)

    @Query("DELETE FROM gamedetailentities")
    suspend fun deleteGameDetail()

    @Query("SELECT * FROM gamescreenshotentities WHERE game_id = :gameId")
    fun getScreenshot(gameId: Int): Flow<List<GameScreenshotEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreenshot(ssList: List<GameScreenshotEntity>)

    @Query("DELETE FROM gamescreenshotentities WHERE game_id = :gameId")
    suspend fun deleteScreenshot(gameId: Int)

    @Query("SELECT * FROM gamemovieentities WHERE game_id = :gameId")
    fun getMovie(gameId: Int): Flow<List<GameMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieList: List<GameMovieEntity>)

    @Query("DELETE FROM gamemovieentities WHERE game_id = :gameId")
    suspend fun deleteMovie(gameId: Int)
}