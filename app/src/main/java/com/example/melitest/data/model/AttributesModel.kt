package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AttributesModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("value_id") var valueId: String? = null,
    @SerializedName("value_name") var valueName: String? = null,
    @SerializedName("attribute_group_id") var attributeGroupId: String? = null,
    @SerializedName("attribute_group_name") var attributeGroupName: String? = null,
    @SerializedName("value_struct") var valueStruct: ValueStructModel? = ValueStructModel(),
    @SerializedName("values") var values: MutableList<ValuesModel>? = null,
    @SerializedName("source") var source: Float? = null,
    @SerializedName("value_type") var valueType: String? = null
) : Serializable
