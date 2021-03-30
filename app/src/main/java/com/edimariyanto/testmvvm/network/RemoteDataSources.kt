package com.edimariyanto.testmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSources {

    companion object {
        private const val BASE_URL = "http://telkomsel.assetwatcher.net:2020/api/v1/"
    }

    fun <Api> buildApi(
        api:Class<Api>
    ) : Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}