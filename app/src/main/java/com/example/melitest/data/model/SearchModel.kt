package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Modelo de datos que representa el resultado de una búsqueda.
 */

data class SearchModel(
    @SerializedName("site_id") var siteId: String? = null,
    @SerializedName("country_default_time_zone") var countryDefaultTimeZone: String? = null,
    @SerializedName("query") var query: String? = null,
    @SerializedName("paging") var paging: PagingModel? = PagingModel(),
    @SerializedName("results") var results: MutableList<ResultsModel>? = null,
    @SerializedName("sort") var sort: SortModel? = SortModel(),
    @SerializedName("available_sorts") var availableSorts: MutableList<AvailableSortsModel>? = null,
    @SerializedName("filters") var filters: MutableList<FiltersModel>? = null,
    @SerializedName("available_filters") var availableFilters: MutableList<AvailableFiltersModel>? = null
) : Serializable
