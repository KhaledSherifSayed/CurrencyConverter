package com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList


import com.google.gson.JsonObject
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.CurrenciesDatabase
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.asListDatabaseModel
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.ApiService
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.utils.toCurrenciesList
import com.ibtikar.mvvm_starter_koin_coroutines.utils.toJsonMap
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

/**
 * Created by Meslmawy on 6/10/2021
 */


class CurrenciesRepository(
    contextProviders: ContextProviders,
    private val apiService: ApiService,
    private val currenciesDatabase: CurrenciesDatabase
) : BaseRepository(contextProviders) {

    fun getCurrenciesListFromDB() = networkHandler {
        currenciesDatabase.currenciesDao.getCurrencies()
    }

    suspend fun getCurrenciesListFromNetwork() = networkHandler {
        apiService.getAllCurrencies()
    }.collect {
        val jsonObject = it.body()?.asJsonObject
        val ratesString = jsonObject?.getAsJsonObject("rates").toString()
        val objMap = ratesString.toJsonMap()
        val list = objMap.toCurrenciesList()
        currenciesDatabase.currenciesDao.insertAll(*list.asListDatabaseModel())
    }

}
