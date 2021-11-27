package com.ilhmdhn.gamecatalog.core.utils

import GameListResult
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel

object DataMapper {
    fun mapListGameResponseToEntities(input: List<GameListResult>): List<GameListEntity>{
        val gameList = ArrayList<GameListEntity>()
        input.map{
            val game = GameListEntity(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapListGameEntityToDomain(input: List<GameListEntity>): List<GameListModel>{
        val gameList = ArrayList<GameListModel>()
        input.map{
            val game = GameListModel(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                rating = it.rating
            )
            gameList.add(game)
        }
        return gameList
    }
}