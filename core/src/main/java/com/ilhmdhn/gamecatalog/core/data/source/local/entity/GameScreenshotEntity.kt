package com.ilhmdhn.gamecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gamescreenshotentities")
data class GameScreenshotEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    
    @ColumnInfo(name = "image")
    val image: String
)
