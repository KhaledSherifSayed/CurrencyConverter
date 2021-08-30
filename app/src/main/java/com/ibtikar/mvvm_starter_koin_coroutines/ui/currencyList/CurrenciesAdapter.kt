package com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.CurrencyModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ItemCurrencyBinding
import com.ibtikar.mvvm_starter_koin_coroutines.utils.hide

/**
 * Created by Meslmawy on 6/10/2021
 */

class CurrenciesAdapter(val callback: CurrencyItemClick) : ListAdapter<CurrencyModelResponse, CurrenciesAdapter.ArticleViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<CurrencyModelResponse>() {
        override fun areItemsTheSame(oldItem: CurrencyModelResponse, newItem: CurrencyModelResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CurrencyModelResponse, newItem: CurrencyModelResponse): Boolean {
            return newItem.currencyValue + newItem.currencyName == oldItem.currencyValue + oldItem.currencyName
        }
    }

    class ArticleViewHolder(val viewDataBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(listener: CurrencyItemClick, news: CurrencyModelResponse, lastPos: Boolean) {
            viewDataBinding.itemClick = listener
            viewDataBinding.item = news
            if(lastPos)
                viewDataBinding.divider.hide()
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            holder.bind(callback,getItem(position),position == itemCount - 1)
        }
    }
}



class CurrencyItemClick(val block: (CurrencyModelResponse) -> Unit) {
    fun onClick(item: CurrencyModelResponse) = block(item)
}