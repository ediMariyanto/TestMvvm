package com.edimariyanto.testmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edimariyanto.testmvvm.data.models.auth.login.LoginResponse
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.repository.UserRepository
import com.edimariyanto.testmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository): BaseViewModel(userRepository) {

    private val _user: MutableLiveData<Resources<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resources<LoginResponse>>
    get() = _user


    fun getUser() = viewModelScope.launch{
        _user.value = Resources.Loading
        _user.value =  userRepository.getUser()

    }

}