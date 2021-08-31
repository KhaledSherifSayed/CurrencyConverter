package com.ibtikar.mvvm_starter_koin_coroutines.data.network

import com.google.gson.JsonObject
import com.ibtikar.mvvm_starter_koin_coroutines.BuildConfig
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.URL.NEWS.URL_GET_CURRENCIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Meslmawy on 6/10/2021
 */

interface ApiService {
    @GET(URL_GET_CURRENCIES)
    suspend fun getAllCurrencies(@Query(value = "access_key") key: String? = BuildConfig.API_KEY): Response<JsonObject>
}