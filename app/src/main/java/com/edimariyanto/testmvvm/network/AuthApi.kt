package com.edimariyanto.testmvvm.network

import com.edimariyanto.testmvvm.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.models.auth.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}