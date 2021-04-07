package com.edimariyanto.testmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.edimariyanto.testmvvm.data.models.auth.login.Data
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.network.UserApi
import com.edimariyanto.testmvvm.data.repository.UserRepository
import com.edimariyanto.testmvvm.databinding.FragmentHomeBinding
import com.edimariyanto.testmvvm.ui.base.BaseFragment
import com.edimariyanto.testmvvm.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.*

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resources.Success -> {
                    binding.progressBar.visible(false)
                    updateUi(it.value.data)
                }

                is Resources.Loading ->{
                    binding.progressBar.visible(true)
                }
            }
        })


        binding.btnLogout.setOnClickListener{
            logout()
        }
    }

    private fun updateUi(user: Data){
        with(binding) {
            tvUserName.text = user.real_name
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}