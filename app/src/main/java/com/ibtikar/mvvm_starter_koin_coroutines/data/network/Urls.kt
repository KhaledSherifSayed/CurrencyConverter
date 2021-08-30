package com.ibtikar.mvvm_starter_koin_coroutines.data.network

import com.ibtikar.mvvm_starter_koin_coroutines.data.network.APIS.CONSTANTS.LATEST

object APIS {

    object CONSTANTS {
        const val LATEST = "latest"
    }

    object URL {

        object NEWS {
            const val URL_GET_CURRENCIES = "$LATEST"
        }

    }
}
