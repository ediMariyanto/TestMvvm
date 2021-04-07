package com.edimariyanto.testmvvm.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edimariyanto.testmvvm.data.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.data.models.auth.login.LoginResponse
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.repository.AuthRepository
import com.edimariyanto.testmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : BaseViewModel(authRepository) {

    private val _loginResponse: MutableLiveData<Resources<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resources<LoginResponse>>
        get() = _loginResponse


    fun login(userName: String, password: String) = viewModelScope.launch{
        _loginResponse.value = Resources.Loading
        _loginResponse.value = authRepository.login(LoginRequest(password, userName))
    }

    suspend fun saveToken(token: String){
        authRepository.saveToken(token)
    }
}