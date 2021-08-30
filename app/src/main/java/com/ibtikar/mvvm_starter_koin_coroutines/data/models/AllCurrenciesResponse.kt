package com.ibtikar.mvvm_starter_koin_coroutines.data.models

import com.google.gson.annotations.SerializedName
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.CurrencyEntity

/**
 * Created by Meslmawy on 6/10/2021
 */


data class CurrencyModelResponse(val currencyName: String, val currencyValue: String)

fun List<CurrencyEntity>.asDomainModel(): List<CurrencyModelResponse> {
    return map {
        CurrencyModelResponse (
            currencyName = it.currencyName,
            currencyValue = it.currencyValue)
    }
}
