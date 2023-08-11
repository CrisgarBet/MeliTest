package com.example.melitest.core.network

import com.example.melitest.data.model.SearchModel
import javax.inject.Inject

class SearchService @Inject constructor(private val api: SearchApiClient) {
    suspend fun searchProducts(site: String, search: String): SearchModel {
        val call = api.searchProducts("MCO/search?q=telefono#json")
        var response: SearchModel = SearchModel()
        if (call.isSuccessful) {
            response = call.body()!!
        }
        return response
    }
}