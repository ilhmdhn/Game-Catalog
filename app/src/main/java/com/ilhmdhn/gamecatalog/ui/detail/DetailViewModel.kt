package com.ilhmdhn.gamecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameDetailModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameMovieModel
import com.ilhmdhn.gamecatalog.core.domain.model.GameScreenshotModel
import com.ilhmdhn.gamecatalog.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase): ViewModel() {

    fun getGameDetail(id: Int, reload: Boolean): LiveData<Resource<GameDetailModel>> {
        return gameUseCase.getDetailGame(id, reload).asLiveData()
    }

    fun getGameScreenshot(gameId: Int, reload: Boolean): LiveData<Resource<List<GameScreenshotModel>>>{
        return gameUseCase.getScreenshotGame(gameId, reload).asLiveData()
    }

    fun getGameMovie(gameId: Int, reload: Boolean): LiveData<Resource<List<GameMovieModel>>>{
        return gameUseCase.getMovieGame(gameId, reload).asLiveData()
    }
}