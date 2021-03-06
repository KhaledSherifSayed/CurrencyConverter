package com.ibtikar.mvvm_starter_koin_coroutines.di

import android.content.Context
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.CurrenciesDatabase
import org.koin.dsl.module

/**
 * Created by Meslmawy on 6/10/2021
 */

val DBModule = module {

    fun provideAppLocalDatabase(context: Context): CurrenciesDatabase {
        return CurrenciesDatabase.getAppDataBase(context)
    }

    // Room Database
    single { provideAppLocalDatabase(get()) }

}