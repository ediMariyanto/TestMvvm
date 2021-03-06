package com.edimariyanto.testmvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edimariyanto.testmvvm.data.repository.AuthRepository
import com.edimariyanto.testmvvm.data.repository.BaseRepository
import com.edimariyanto.testmvvm.data.repository.UserRepository
import com.edimariyanto.testmvvm.ui.auth.AuthViewModel
import com.edimariyanto.testmvvm.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository : BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            // TODO : Add another viewModel here
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}