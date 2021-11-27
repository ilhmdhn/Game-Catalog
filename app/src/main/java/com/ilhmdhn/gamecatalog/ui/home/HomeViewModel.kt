package com.ilhmdhn.gamecatalog.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilhmdhn.gamecatalog.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase): ViewModel() {
    val game = gameUseCase.getListGame().asLiveData()
}