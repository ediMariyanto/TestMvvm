package com.edimariyanto.testmvvm.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edimariyanto.testmvvm.data.models.auth.login.LoginRequest
import com.edimariyanto.testmvvm.data.models.auth.login.LoginResponse
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resources<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resources<LoginResponse>>
        get() = _loginResponse


    fun login(userName: String, password: String) = viewModelScope.launch{
        _loginResponse.value = authRepository.login(LoginRequest(password, userName))
    }
}