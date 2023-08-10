package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class SearchModel(
    @SerializedName("site_id") var siteId: String? = null,
    @SerializedName("country_default_time_zone") var countryDefaultTimeZone: String? = null,
    @SerializedName("query") var query: String? = null,
    @SerializedName("paging") var paging: PagingModel? = PagingModel(),
    @SerializedName("results") var results: ArrayList<ResultsModel> = arrayListOf(),
    @SerializedName("sort") var sort: SortModel? = SortModel(),
    @SerializedName("available_sorts") var availableSorts: ArrayList<AvailableSortsModel> = arrayListOf(),
    @SerializedName("filters") var filters: ArrayList<FiltersModel> = arrayListOf(),
    @SerializedName("available_filters") var availableFilters: ArrayList<AvailableFiltersModel> = arrayListOf()
)
