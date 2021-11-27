package com.ilhmdhn.gamecatalog.core.domain.repository

import com.ilhmdhn.gamecatalog.core.data.Resource
import com.ilhmdhn.gamecatalog.core.domain.model.GameListModel
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getListGame(): Flow<Resource<List<GameListModel>>>
}