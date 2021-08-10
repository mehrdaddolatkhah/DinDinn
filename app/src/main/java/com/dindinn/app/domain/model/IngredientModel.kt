package com.dindinn.app.domain.model

import com.google.gson.annotations.SerializedName

data class IngredientModel(
    @SerializedName("category_id") val categoryId: Int?,
    @SerializedName("category_name") val categoryName: String?,
    @SerializedName("foods") val foodList: List<FoodModel>
)

data class FoodModel(
    @SerializedName("name") val name: String?,
    @SerializedName("picture") val picture: String?,
    @SerializedName("quantity") val quantity: Int?,
    @SerializedName("is_available") val isAvailable: Boolean?,
)