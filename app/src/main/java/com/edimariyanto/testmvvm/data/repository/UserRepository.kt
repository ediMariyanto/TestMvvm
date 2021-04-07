package com.edimariyanto.testmvvm.data.repository

import com.edimariyanto.testmvvm.data.network.UserApi

class UserRepository(private val api: UserApi) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}