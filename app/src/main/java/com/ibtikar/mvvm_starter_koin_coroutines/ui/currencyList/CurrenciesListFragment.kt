package com.ibtikar.mvvm_starter_koin_coroutines.ui.currencyList


import androidx.navigation.fragment.findNavController
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.CurrenciesListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import kotlinx.android.synthetic.main.currencies_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Meslmawy on 6/10/2021
 */

class CurrenciesListFragment :
    BaseFragmentWithBusiness<CurrenciesListFragmentBinding, CurrenciesViewModel>(R.layout.currencies_list_fragment) {

    private var currenciesAdapter: CurrenciesAdapter? = null
    override val viewModel: CurrenciesViewModel by viewModel()

    override fun setup() {
        setupAdapter()
        viewModel.getLocalData()
        viewModel.getNewCurrencies()
    }

    private fun setupAdapter() {
        currenciesAdapter = CurrenciesAdapter(CurrencyItemClick {
            findNavController().navigate(
                CurrenciesListFragmentDirections.actionHomeFragmentToFavoriteListFragment(
                    it.currencyName,
                    it.currencyValue
                )
            )
        })
        articlesRV.adapter = currenciesAdapter
    }

    override fun render(state: ViewState) {
        when (state) {
            is CurrenciesViewState.onCurrenciesResponse -> {
                currenciesAdapter?.submitList(state.data)
            }
        }
    }
}
