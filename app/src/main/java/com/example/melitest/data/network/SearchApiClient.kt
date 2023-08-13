package com.example.melitest.data.network

import com.example.melitest.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchApiClient {
    @GET("{option}/search")
    suspend fun searchProducts(
        @Path("option") site: String?, @Query("q") query: String
    ): Response<SearchModel>
}