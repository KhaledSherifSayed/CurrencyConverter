package com.ibtikar.mvvm_starter_koin_coroutines.ui.calculator


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.CalculatorFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import kotlinx.android.synthetic.main.calculator_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Meslmawy on 6/10/2021
 */

class CalculatorFragment : BaseFragmentWithBusiness<CalculatorFragmentBinding,CalculatorViewModel>(R.layout.calculator_fragment) {

    private val args: CalculatorFragmentArgs by navArgs()
    override val viewModel: CalculatorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binder.apply {
            this.lifecycleOwner = this@CalculatorFragment
            this.vm = viewModel
            executePendingBindings()
        }.root
    }


    override fun setup() {
        secondary_currency_title.text = args.title
        secondary_currency_value.text = args.value
    }

    override fun render(state: ViewState) {

    }
}
