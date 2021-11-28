package com.ilhmdhn.gamecatalog.core.domain.model

import androidx.room.ColumnInfo

data class GameMovieModel(
    val id: Int?,
    val gameId: Int?,
    val movie: String?
)
