package com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.asDomainModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.flow.collect

/**
 * Created by Meslmawy on 6/10/2021
 */

class CurrenciesViewModel(
    private val contextProviders: ContextProviders,
    private val currenciesRepository: CurrenciesRepository
) :
    BaseViewModel(contextProviders) {

    fun getLocalData() {
        launchBlock(showLoading = false) {
            currenciesRepository.getCurrenciesListFromDB().collect {
                it.collect { list ->
                    setState(CurrenciesViewState.onCurrenciesResponse(list.asDomainModel()))
                }
            }
        }
    }


    fun getNewCurrencies() {
        launchBlock(showLoading = true) {
            currenciesRepository.getCurrenciesListFromNetwork()
        }
    }

}
