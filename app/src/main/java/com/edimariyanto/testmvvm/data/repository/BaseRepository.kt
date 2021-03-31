package com.edimariyanto.testmvvm.data.repository

import com.edimariyanto.testmvvm.data.network.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resources<T>{
        return withContext(Dispatchers.IO){
            try {
                Resources.Success(apiCall.invoke())
            } catch (throwable: Throwable){
                when(throwable) {
                    is HttpException ->{
                        Resources.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    } else ->{
                        Resources.Failure(true, null, null)
                    }
                }
            }
        }
    }
}