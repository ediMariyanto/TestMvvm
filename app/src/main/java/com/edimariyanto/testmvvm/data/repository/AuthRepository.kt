package com.edimariyanto.testmvvm.data.repository

import com.edimariyanto.testmvvm.data.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.data.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {

    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.login(loginRequest)
    }

}