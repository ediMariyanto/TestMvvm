package com.edimariyanto.testmvvm.repository

import com.edimariyanto.testmvvm.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {

    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.login(loginRequest)
    }

}