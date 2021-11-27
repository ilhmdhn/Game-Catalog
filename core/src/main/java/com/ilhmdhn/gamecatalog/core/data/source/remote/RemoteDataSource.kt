package com.ilhmdhn.gamecatalog.core.data.source.remote

import GameListResult
import android.annotation.SuppressLint
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiResponse
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListGame(): Flow<ApiResponse<List<GameListResult>>> {
       return flow{
           try{
               val response = apiService.getGameList()
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
}