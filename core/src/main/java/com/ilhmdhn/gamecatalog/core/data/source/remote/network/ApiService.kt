package com.ilhmdhn.gamecatalog.core.data.source.remote.network

import GameListResponse
import com.ilhmdhn.gamecatalog.core.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(
        @Query("key") key: String = BuildConfig.API_KEY
    ): GameListResponse
}