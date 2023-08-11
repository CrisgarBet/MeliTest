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

    fun onCreate() {
        viewModelScope.launch {
            val result: SearchModel? = searchProductsUseCase.searchProduct("MCO", "telefono")
            if (result!=null) {
                searchModel.postValue(result)
            }
        }
    }
}