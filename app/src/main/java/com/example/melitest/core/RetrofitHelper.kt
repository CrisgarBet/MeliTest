package com.example.melitest.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/sites/MCO/search?q=telefono")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}