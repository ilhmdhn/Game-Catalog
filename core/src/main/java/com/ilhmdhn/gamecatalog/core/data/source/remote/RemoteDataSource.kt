package com.ilhmdhn.gamecatalog.core.data.source.remote

import GameListResult
import android.annotation.SuppressLint
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiResponse
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiService
import com.ilhmdhn.gamecatalog.core.data.source.remote.response.GameDetailResponse
import com.ilhmdhn.imageslider.model.ResultsImageItem
import com.ilhmdhn.imageslider.model.ResultsItemMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListGame(search: String): Flow<ApiResponse<List<GameListResult>>> {
       return flow{
           try{
               val response = apiService.getGameList(search)
               val dataArray = response.results
               if (dataArray.isNotEmpty()){
                   emit(ApiResponse.Success(response.results))
               } else {
                   emit(ApiResponse.Empty)
               }
           } catch (e: Exception){
               emit(ApiResponse.Error(e.toString()))
           }
       }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: Int): Flow<ApiResponse<GameDetailResponse>>{
        return flow{
            try{
                val detailGame = apiService.getGameDetail(id)
                emit(ApiResponse.Success(detailGame))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getScreenshotGame(id: Int): Flow<ApiResponse<List<ResultsImageItem>>>{
        return flow{
            try {
                val response = apiService.getScreenshot(id)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: java.lang.Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(id: Int): Flow<ApiResponse<List<ResultsItemMovie>>>{
        return flow{
            try {
                val response = apiService.getMovie(id)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: java.lang.Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}