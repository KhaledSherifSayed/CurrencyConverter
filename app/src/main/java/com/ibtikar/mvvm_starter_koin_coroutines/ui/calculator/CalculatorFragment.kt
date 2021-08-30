package com.ibtikar.mvvm_starter_koin_coroutines.ui.calculator


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.CalculatorFragmentBinding
import kotlinx.android.synthetic.main.calculator_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Meslmawy on 6/10/2021
 */

class CalculatorFragment : Fragment(R.layout.calculator_fragment) {

    private val args: CalculatorFragmentArgs by navArgs()
    private val viewModel: CalculatorViewModel by viewModel()
    lateinit var binding: CalculatorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.calculator_fragment, container, false
        )
        return binding.apply {
            this.lifecycleOwner = this@CalculatorFragment
            this.vm = viewModel
            executePendingBindings()
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondary_currency_title.text = args.title
        secondary_currency_value.text = args.value
    }
}
