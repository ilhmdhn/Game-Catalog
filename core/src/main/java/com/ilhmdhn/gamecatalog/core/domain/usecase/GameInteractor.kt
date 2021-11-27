package com.ilhmdhn.gamecatalog.core.domain.usecase

import com.ilhmdhn.gamecatalog.core.domain.repository.IGameRepository

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getListGame() = gameRepository.getListGame()
}