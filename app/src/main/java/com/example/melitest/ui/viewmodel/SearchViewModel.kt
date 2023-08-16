package com.example.melitest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melitest.data.enums.Site
import com.example.melitest.data.model.SearchModel
import com.example.melitest.domain.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Administra la capa UI del activity que muestra el resultado de la búsqueda.
 * @param searchProductsUseCase capa de datos que nos suminstra el repository con el que se está trabajando.
 */

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchProductsUseCase: SearchProductsUseCase) :
    ViewModel() {

    val searchModel = MutableLiveData<SearchModel?>()
    val loadOptions = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData(false)

    fun onCreate() {
        loadOptions()
    }

    /**
     * Función encardaga de realizar el llamado a la api para obtener una búsqueda.
     * @param site hace referencia al sition de mercadolibre que se tiene seleccionado.
     * @param query hace referencia a lo que ingresó el usuario para realizar la búsqueda.
     */
    fun searchProducts(site: String?, query: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: SearchModel? = searchProductsUseCase.searchProduct(site, query)
            if (result != null) {
                searchModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    /**
     * Función encargada de cargar las opciones de los sitios de mercadolibre para la búsqueda.
     */
    private fun loadOptions() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = Site.values().map { it.name }
            loadOptions.postValue(result)
            isLoading.postValue(false)
        }
    }
}