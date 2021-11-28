package com.ilhmdhn.gamecatalog.core.domain.usecase

import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import com.ilhmdhn.gamecatalog.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getListGame(search: String, reload: Boolean) = gameRepository.getListGame(search, reload)
    override fun getDetailGame(id: Int, reload: Boolean) = gameRepository.getDetailGame(id, reload)

    override fun getScreenshotGame(gameId: Int,reload: Boolean) = gameRepository.getScreenshotGame(gameId, reload)

    override fun getMovieGame(gameId: Int, reload: Boolean) = gameRepository.getMovieGame(gameId, reload)
}