package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Meslmawy on 6/10/2021
 */

@Dao
interface CurrenciesDao {

    @Query("select * from CurrencyEntity")
    fun getCurrencies(): Flow<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg currencies: CurrencyEntity)

}

@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = false)
abstract class CurrenciesDatabase : RoomDatabase() {
    abstract val currenciesDao: CurrenciesDao

    companion object {
        @Volatile
        private var INSTANCE: CurrenciesDatabase? = null

        fun getAppDataBase(context: Context): CurrenciesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    CurrenciesDatabase::class.java,
                    "currenciesDB"
                ).build()
            }.also {
                INSTANCE = it
            }
        }
    }
}

