package com.example.melitest.data

import com.example.melitest.data.model.SearchModel
import com.example.melitest.data.network.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SearchService) {
    suspend fun searchProducts(site: String?, search: String?): SearchModel {
        return api.searchProducts(site, search)
    }
}