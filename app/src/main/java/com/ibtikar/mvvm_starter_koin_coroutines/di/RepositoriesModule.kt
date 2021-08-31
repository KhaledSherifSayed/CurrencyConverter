package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList.CurrenciesRepository
import org.koin.dsl.module

/**
 * Created by Meslmawy on 6/10/2021
 */

val repositoryModule = module {
    single {
        CurrenciesRepository(get(), get(), get())
    }

}