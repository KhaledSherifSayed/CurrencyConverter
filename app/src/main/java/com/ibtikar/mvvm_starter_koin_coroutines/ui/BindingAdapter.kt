package com.ibtikar.mvvm_starter_koin_coroutines.ui


import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import java.math.RoundingMode
import java.text.DecimalFormat


@BindingAdapter("formatCurrencyValue")
fun formatCurrencyValue(view: AppCompatTextView, value: String?) {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    view.text = df.format(value?.toDouble())
}