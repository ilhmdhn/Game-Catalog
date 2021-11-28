package com.ilhmdhn.gamecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gamedetailentities")
data class GameDetailEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    val id: Int,

    @ColumnInfo(name ="background_image")
    val backgroundImage: String?,

    @ColumnInfo(name ="name")
    val name: String?,

    @ColumnInfo(name ="name_original")
    val nameOriginal: String?,

    @ColumnInfo(name ="description_raw")
    val description: String?,

    @ColumnInfo(name ="rating")
    val rating: Float?,

    @ColumnInfo(name ="released")
    val released: String?,

    @ColumnInfo(name ="website")
    val website: String?
)
