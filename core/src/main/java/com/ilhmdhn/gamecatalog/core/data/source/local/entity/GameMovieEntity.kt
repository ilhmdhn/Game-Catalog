package com.ilhmdhn.gamecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gamemovieentities")
data class GameMovieEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "game_id")
    val gameId: Int?,

    @ColumnInfo(name = "movie")
    val movie: String?
)