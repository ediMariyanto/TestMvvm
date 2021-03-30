package com.edimariyanto.testmvvm.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.edimariyanto.testmvvm.R
import com.edimariyanto.testmvvm.databinding.FragmentLoginBinding
import com.edimariyanto.testmvvm.network.AuthApi
import com.edimariyanto.testmvvm.network.Resources
import com.edimariyanto.testmvvm.repository.AuthRepository
import com.edimariyanto.testmvvm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is Resources.Success -> {
                    Toast.makeText(requireContext(), "Berhasil, "+it.toString(), Toast.LENGTH_LONG).show()
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