package com.ilhmdhn.gamecatalog.core.domain.repository

import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getListGame(search: String, reload: Boolean): Flow<Resource<List<GameListModel>>>

    fun getDetailGame(id: Int, reload: Boolean): Flow<Resource<GameDetailModel>>

    fun getScreenshotGame(gameId: Int, reload: Boolean): Flow<Resource<List<GameScreenshotModel>>>

    fun getMovieGame(gameId: Int, reload: Boolean): Flow<Resource<List<GameMovieModel>>>
}