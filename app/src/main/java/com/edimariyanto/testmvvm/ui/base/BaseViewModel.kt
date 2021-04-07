package com.edimariyanto.testmvvm.ui.base

import androidx.lifecycle.ViewModel
import com.edimariyanto.testmvvm.data.network.UserApi
import com.edimariyanto.testmvvm.data.repository.BaseRepository

abstract class BaseViewModel(private val baseRepository: BaseRepository) : ViewModel() {

    suspend fun logout(api: UserApi) = baseRepository.logout(api)

}