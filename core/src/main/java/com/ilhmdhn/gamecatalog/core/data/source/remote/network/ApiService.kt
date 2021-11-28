package com.ilhmdhn.gamecatalog.core.data.source.remote.network

import GameListResponse
import com.ilhmdhn.gamecatalog.core.BuildConfig
import com.ilhmdhn.gamecatalog.core.data.source.remote.response.GameDetailResponse
import com.ilhmdhn.imageslider.model.ImageResponse
import com.ilhmdhn.imageslider.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(
        @Query("search") search: String = "",
        @Query("page_size") size: Int = 20,
        @Query("key") key: String = BuildConfig.API_KEY
    ): GameListResponse

    @GET("games/{id}")
     suspend fun getGameDetail(
        @Path("id") idGame: Int,
        @Query("key") key: String = BuildConfig.API_KEY
    ): GameDetailResponse

    @GET("games/{id}/screenshots")
     suspend fun getScreenshot(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.API_KEY
    ): ImageResponse

    @GET("games/{id}/movies")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.API_KEY
    ): TrailerResponse
}