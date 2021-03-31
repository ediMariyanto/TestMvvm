package com.edimariyanto.testmvvm.data.network

import com.edimariyanto.testmvvm.data.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.data.models.auth.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}