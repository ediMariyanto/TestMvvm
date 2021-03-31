package com.edimariyanto.testmvvm.network

import com.edimariyanto.testmvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSources {

    companion object {
        private const val BASE_URL = "http://telkomsel.assetwatcher.net:2020/api/v1/"
    }

    fun <Api> buildApi(
        api: Class<Api>
    ) : Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also {
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        it.addInterceptor(logging)
                    }
                    it.connectTimeout(60, TimeUnit.SECONDS)
                    it.readTimeout(60, TimeUnit.SECONDS)
                    it.writeTimeout(60, TimeUnit.SECONDS)
                    it.addInterceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .header("X-App-Version", BuildConfig.VERSION_NAME)

                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                        }.build()
                    )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}

//client.writeTimeout(60, TimeUnit.SECONDS)
//client.addInterceptor(
//new Interceptor () {
