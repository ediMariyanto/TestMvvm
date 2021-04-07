package com.edimariyanto.testmvvm.data.repository

import com.edimariyanto.testmvvm.data.UserPreferences
import com.edimariyanto.testmvvm.data.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.data.network.AuthApi

class AuthRepository(
        private val api: AuthApi,
        private val userPreferences: UserPreferences

) : BaseRepository() {



    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.login(loginRequest)
    }

    suspend fun saveToken(token: String) {
        userPreferences.saveAuthToken(token)
    }

}