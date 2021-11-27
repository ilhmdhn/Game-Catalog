package com.ilhmdhn.gamecatalog.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity

@Database(entities = [GameListEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}