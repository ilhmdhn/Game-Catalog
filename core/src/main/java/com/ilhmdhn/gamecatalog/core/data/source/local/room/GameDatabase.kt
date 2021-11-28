package com.ilhmdhn.gamecatalog.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameDetailEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameMovieEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameScreenshotEntity

@Database(entities = [GameListEntity::class, GameDetailEntity::class, GameScreenshotEntity::class, GameMovieEntity::class], version = 2, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}