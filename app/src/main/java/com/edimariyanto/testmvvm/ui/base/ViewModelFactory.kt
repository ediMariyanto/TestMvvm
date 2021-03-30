package com.edimariyanto.testmvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edimariyanto.testmvvm.repository.AuthRepository
import com.edimariyanto.testmvvm.repository.BaseRepository
import com.edimariyanto.testmvvm.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository : BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            // TODO : Add another viewModel here
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}