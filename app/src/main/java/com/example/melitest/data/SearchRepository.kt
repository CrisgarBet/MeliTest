package com.example.melitest.data

import android.util.Log
import com.example.melitest.data.model.SearchModel
import com.example.melitest.data.network.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SearchService) {
    suspend fun searchProducts(site: String?, search: String?): SearchModel {
        var result: SearchModel = SearchModel()
        try {
            result = api.searchProducts(site, search)
        } catch (e: Exception) {
            Log.e("SearchRepository", e.localizedMessage)
        }
        return result
    }
}