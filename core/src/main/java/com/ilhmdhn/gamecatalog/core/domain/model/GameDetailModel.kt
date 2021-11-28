package com.ilhmdhn.gamecatalog.core.domain.model

data class GameDetailModel(
    val id: Int?,
    val backgroundImage: String?,
    val name: String?,
    val nameOriginal: String?,
    val description: String?,
    val rating: Float?,
    val released: String?,
    val website: String?
)
