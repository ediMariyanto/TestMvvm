package com.edimariyanto.testmvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.edimariyanto.testmvvm.data.network.RemoteDataSources
import com.edimariyanto.testmvvm.data.repository.BaseRepository

abstract class BaseFragment<VM: ViewModel, B: ViewBinding, R: BaseRepository> : Fragment() {


    protected lateinit var binding: B
    protected val remoteDataSource =  RemoteDataSources()
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        return binding.root
    }


    abstract fun getViewModel() :  Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R
}