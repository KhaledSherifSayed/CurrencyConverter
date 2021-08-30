package com.ibtikar.mvvm_starter_koin_coroutines.ui.calculator

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.CurrencyModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState

/**
 * Created by Meslmawy on 6/10/2021
 */


sealed class CurrenciesViewState : ViewState() {
    data class onEnterBigValuewResponse(val data: List<CurrencyModelResponse>?) : Loaded<List<CurrencyModelResponse>?>(data)
}
