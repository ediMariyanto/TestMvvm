package com.edimariyanto.testmvvm.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.edimariyanto.testmvvm.data.network.AuthApi
import com.edimariyanto.testmvvm.data.network.Resources
import com.edimariyanto.testmvvm.data.repository.AuthRepository
import com.edimariyanto.testmvvm.databinding.FragmentLoginBinding
import com.edimariyanto.testmvvm.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resources.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Berhasil, " + it.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Resources.Failure -> {
                    Toast.makeText(requireContext(), "gagal" + ", ", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.btnSubmitLogin.setOnClickListener {
            val userName = binding.etUsernameLogin.text.toString().trim()
            val password = binding.etPasswordLogin.text.toString()

            viewModel.login(userName, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)


    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}