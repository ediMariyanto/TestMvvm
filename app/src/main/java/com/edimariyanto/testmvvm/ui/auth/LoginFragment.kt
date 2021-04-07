package com.edimariyanto.testmvvm.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.edimariyanto.testmvvm.data.network.AuthApi
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.repository.AuthRepository
import com.edimariyanto.testmvvm.databinding.FragmentLoginBinding
import com.edimariyanto.testmvvm.ui.base.BaseFragment
import com.edimariyanto.testmvvm.ui.enable
import com.edimariyanto.testmvvm.ui.handleApiError
import com.edimariyanto.testmvvm.ui.home.HomeActivity
import com.edimariyanto.testmvvm.ui.startNewActivity
import com.edimariyanto.testmvvm.ui.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressLogin.visible(false)
        binding.btnSubmitLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressLogin.visible(it is Resources.Loading)
            when (it) {
                is Resources.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveToken(it.value.data.access_token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }

                is Resources.Failure -> handleApiError(it) {login()}
            }
        })

        binding.etPasswordLogin.addTextChangedListener{
            val email = binding.etUsernameLogin.text.toString().trim()
            binding.btnSubmitLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.btnSubmitLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        binding.progressLogin.visible(true)
        val userName = binding.etUsernameLogin.text.toString().trim()
        val password = binding.etPasswordLogin.text.toString()

        viewModel.login(userName, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)


    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}