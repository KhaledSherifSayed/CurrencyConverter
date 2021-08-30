package com.ibtikar.mvvm_starter_koin_coroutines.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.CurrencyModelResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import kotlin.reflect.full.memberProperties
import com.google.gson.reflect.TypeToken


inline fun <reified T> getKoinInstance(): Lazy<T> {
    return lazy {
        return@lazy object : KoinComponent {
            val value: T by inject()
        }.value
    }
}



fun View.show() {
    visibility = View.VISIBLE
}

fun show(vararg views: View) {
    views.forEach { it.show() }
}

fun View.hide() {
    visibility = View.GONE
}

fun hide(vararg views: View) {
    views.forEach { it.hide() }
}

fun View.invisible() {
    visibility = View.INVISIBLE

}

fun invisible(vararg views: View) {
    views.forEach { it.invisible() }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


fun EditText.clear() { text.clear() }

fun String.toJsonMap(): Map<String, Any> {
    return try {
        Gson().fromJson<Any>(
            this,
            object : TypeToken<Map<String, Any>>() {}.type
        ) as Map<String, Any>
    } catch (e: Exception) {
        mapOf()
    }
}

fun Map<String, Any>.toCurrenciesList() =
    this.map { CurrencyModelResponse(it.key, it.value.toString()) }



