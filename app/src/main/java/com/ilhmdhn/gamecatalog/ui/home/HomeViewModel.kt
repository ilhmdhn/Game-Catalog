package com.ilhmdhn.gamecatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import com.ilhmdhn.gamecatalog.core.domain.usecase.GameUseCase

class HomeViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun getGameList(search: String, reload: Boolean): LiveData<Resource<List<GameListModel>>>{
        return gameUseCase.getListGame(search, reload).asLiveData()
    }
}