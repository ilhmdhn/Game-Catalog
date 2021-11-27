package com.ilhmdhn.gamecatalog.core.domain.usecase

import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getListGame(): Flow<Resource<List<GameListModel>>>
}