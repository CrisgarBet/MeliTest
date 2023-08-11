package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class ResultsModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("condition") var condition: String? = null,
    @SerializedName("thumbnail_id") var thumbnailId: String? = null,
    @SerializedName("catalog_product_id") var catalogProductId: String? = null,
    @SerializedName("listing_type_id") var listingTypeId: String? = null,
    @SerializedName("permalink") var permalink: String? = null,
    @SerializedName("buying_mode") var buyingMode: String? = null,
    @SerializedName("site_id") var siteId: String? = null,
    @SerializedName("category_id") var categoryId: String? = null,
    @SerializedName("domain_id") var domainId: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("currency_id") var currencyId: String? = null,
    @SerializedName("order_backend") var orderBackend: Float? = null,
    @SerializedName("price") var price: Float? = null,
    @SerializedName("original_price") var originalPrice: String? = null,
    @SerializedName("sale_price") var salePrice: String? = null,
    @SerializedName("sold_quantity") var soldQuantity: Float? = null,
    @SerializedName("available_quantity") var availableQuantity: Float? = null,
    @SerializedName("official_store_id") var officialStoreId: String? = null,
    @SerializedName("use_thumbnail_id") var useThumbnailId: Boolean? = null,
    @SerializedName("accepts_mercadopago") var acceptsMercadopago: Boolean? = null,
    @SerializedName("tags") var tags: MutableList<String>? = null,
    @SerializedName("shipping") var shipping: ShippingModel? = ShippingModel(),
    @SerializedName("stop_time") var stopTime: String? = null,
    @SerializedName("seller") var seller: SellerModel? = SellerModel(),
    @SerializedName("seller_address") var sellerAddress: SellerAddressModel? = SellerAddressModel(),
    @SerializedName("address") var address: AddressModel? = AddressModel(),
    @SerializedName("attributes") var attributes: MutableList<AttributesModel>? = null,
    @SerializedName("installments") var installments: InstallmentsModel? = InstallmentsModel(),
    @SerializedName("winner_item_id") var winnerItemId: String? = null,
    @SerializedName("catalog_listing") var catalogListing: Boolean? = null,
    @SerializedName("discounts") var discounts: String? = null,
    @SerializedName("promotions") var promotions: MutableList<String>? = null,
    @SerializedName("inventory_id") var inventoryId: String? = null
)
