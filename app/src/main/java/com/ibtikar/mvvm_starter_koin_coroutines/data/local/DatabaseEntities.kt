package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.CurrencyModelResponse


@Entity
data class CurrencyEntity constructor(
    @PrimaryKey
    val id: String,
    val currencyName: String,
    val currencyValue: String)

fun List<CurrencyModelResponse>.asListDatabaseModel() : Array<CurrencyEntity>  {
    return this.map {
        CurrencyEntity(
            id = it.currencyName + it.currencyValue,
            currencyName = it.currencyName,
            currencyValue = it.currencyValue)
    }.toTypedArray()
}
