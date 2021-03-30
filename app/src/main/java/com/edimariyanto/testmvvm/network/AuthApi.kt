package com.edimariyanto.testmvvm.network

import com.edimariyanto.testmvvm.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.models.auth.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

//    @FormUrlEncoded
//    @POST("login")
//    fun login(@Field("userName") userName: String, @Field("password") pass: String) : Any

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}