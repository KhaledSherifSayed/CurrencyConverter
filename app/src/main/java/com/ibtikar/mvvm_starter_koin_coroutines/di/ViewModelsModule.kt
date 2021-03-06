package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.calculator.CalculatorViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList.CurrenciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Meslmawy on 6/10/2021
 */

val viewModelModule = module {
    viewModel {
        CurrenciesViewModel(get(), get())
    }

    viewModel {
        CalculatorViewModel(get())
    }
}