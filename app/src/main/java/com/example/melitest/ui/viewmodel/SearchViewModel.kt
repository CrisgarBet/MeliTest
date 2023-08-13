package com.example.melitest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melitest.data.model.SearchModel
import com.example.melitest.domain.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchProductsUseCase: SearchProductsUseCase) :
    ViewModel() {

    val searchModel = MutableLiveData<SearchModel?>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun onCreate() {

    }

    fun searchProducts(site: String?, query: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: SearchModel? = searchProductsUseCase.searchProduct(site, query)
            if (result != null) {
                searchModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}