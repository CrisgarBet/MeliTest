package com.example.melitest.domain

import com.example.melitest.data.SearchRepository
import com.example.melitest.data.model.SearchModel
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val repository: SearchRepository) {

    suspend fun searchProduct(site: String?, search: String): SearchModel? {
        return repository.searchProducts(site, search)
    }

}