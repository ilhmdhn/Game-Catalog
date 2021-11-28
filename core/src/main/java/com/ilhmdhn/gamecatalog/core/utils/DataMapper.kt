package com.ilhmdhn.gamecatalog.core.utils

import GameListResult
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameDetailEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameListEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameMovieEntity
import com.ilhmdhn.gamecatalog.core.data.source.local.entity.GameScreenshotEntity
import com.ilhmdhn.gamecatalog.core.data.source.remote.response.GameDetailResponse
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import com.ilhmdhn.imageslider.model.ResultsImageItem
import com.ilhmdhn.imageslider.model.ResultsItemMovie

object DataMapper {
    fun mapListGameResponseToEntity(input: List<GameListResult>): List<GameListEntity>{
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

    fun mapDetailGameResponseToEntity(input: GameDetailResponse): GameDetailEntity{
        return with(input){
            GameDetailEntity(
                id, backgroundImage, name, nameOriginal, description, rating, released, website
            )
        }
    }

    fun mapDetailGameEntityToDomain(input: GameDetailEntity?): GameDetailModel =
        with(input){
            GameDetailModel(
                this?.id,
                this?.backgroundImage,
                this?.name,
                this?.nameOriginal,
                this?.description,
                this?.rating,
                this?.released,
                this?.website
            )
        }

    fun mapScreenshotGameResponseToEntity(idGame: Int, input: List<ResultsImageItem>): List<GameScreenshotEntity>{
        val ssList = ArrayList<GameScreenshotEntity>()
        input.map{
            val ss = GameScreenshotEntity(
                id = it.id,
                gameId = idGame,
                image = it.image
            )
            ssList.add(ss)
        }
        return ssList
    }

    fun mapScreenshotGameEntityToDomain(input: List<GameScreenshotEntity>): List<GameScreenshotModel>{
        val ssList = ArrayList<GameScreenshotModel>()
        input.map{
            val ss = GameScreenshotModel(
                id = it.id,
                gameId = it.gameId,
                image = it.image
            )
            ssList.add(ss)
        }
        return ssList
    }

    fun mapMovieGameResponseToEntity(idGame: Int, input: List<ResultsItemMovie>): List<GameMovieEntity>{
        val movieList = ArrayList<GameMovieEntity>()
        input.map{
            val movie = GameMovieEntity(
                id = it.id,
                gameId = idGame,
                movie = it.dataMovie.jsonMember480
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieGameEntityToDomain(input: List<GameMovieEntity>): List<GameMovieModel>{
        val movieList = ArrayList<GameMovieModel>()
        input.map{
            val movie = GameMovieModel(
                id = it.id,
                gameId = it.gameId,
                movie = it.movie
            )
            movieList.add(movie)
        }
        return movieList
    }
}