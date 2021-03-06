package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

abstract class BaseFragment<T : ViewDataBinding>(private val layoutId: Int) :
    Fragment() {

    /**
     *  in case we needed to access the views
     */
    lateinit var binder: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binder.root
    }

}

abstract class BaseFragmentWithBusiness<T : ViewDataBinding, VM : BaseViewModel>(layoutId: Int) :
    BaseFragment<T>(layoutId) {

    protected abstract val viewModel: VM

    private lateinit var loadingDialog: LoaderDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            loadingDialog = LoaderDialog[it]
        }
        setup()
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                baseRender(it)
            }
        }
    }

    abstract fun setup()

    private fun baseRender(state: ViewState) {
        when (state) {
            is ViewState.Loading -> if (state.loading) showLoading() else hideLoading()
            is ViewState.Error -> showError(state)
            else -> {
                hideLoading()
                render(state)
            }
        }
    }

    abstract fun render(state: ViewState)

    // not private for the sake of overriding in case of custom implementation for specific screens
    fun showLoading() {
        loadingDialog.show()
    }

    // not private for the sake of overriding in case of custom implementation for specific screens
    fun hideLoading() {
        loadingDialog.hide()
    }

    fun showError(error: ViewState.Error) {
        hideLoading()
        val errorMessage = error.exception.errorMessage
            ?: run {
                return@run if (error.exception.errorMessageRes != null) {
                    getString(error.exception.errorMessageRes)
                } else null
            }
            ?: "Unexpected Error"
        if (errorMessage.isNotEmpty())
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}