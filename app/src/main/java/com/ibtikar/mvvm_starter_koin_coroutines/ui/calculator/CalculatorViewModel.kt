package com.ibtikar.mvvm_starter_koin_coroutines.ui.calculator

import androidx.lifecycle.MutableLiveData
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ApplicationException
import com.ibtikar.mvvm_starter_koin_coroutines.utils.erros.ErrorType

/**
 * Created by Meslmawy on 6/10/2021
 */

class CalculatorViewModel(
    private val contextProviders: ContextProviders
) :
    BaseViewModel(contextProviders) {

    val baseCurrencyValue = MutableLiveData<String>().apply { value = "0" }
    val secondaryCurrencyValue = MutableLiveData<String>()
    val result = MutableLiveData<String>().apply { value = "0.0" }

    fun addBaseCurrencyNumber(text: Int) {
        if (text == 0 && result.value?.toDouble() == 0.0)
            return
        else {
            when {
                baseCurrencyValue.value?.length!! > 15 -> {

                }
                result.value?.toDouble() == 0.0 -> baseCurrencyValue.value = text.toString()
                else -> baseCurrencyValue.value = baseCurrencyValue.value + text.toString()
            }
        }
        calculateNewResult()
    }

    fun backspaceValue() {
        if (baseCurrencyValue.value?.length == 1) {
            baseCurrencyValue.value = "0"
            result.value = "0.0"
        } else {
            baseCurrencyValue.value = baseCurrencyValue.value?.dropLast(1)
            calculateNewResult()
        }
    }

    fun clearValue() {
        baseCurrencyValue.value = "0"
        result.value = "0.0"
    }

    private fun calculateNewResult() {
        val newBase = baseCurrencyValue.value?.toLong()
        val secondary = secondaryCurrencyValue.value?.toDouble()
        if (newBase != null && secondary != null) {
            result.value = (newBase * secondary).toString()
        }
    }
}
