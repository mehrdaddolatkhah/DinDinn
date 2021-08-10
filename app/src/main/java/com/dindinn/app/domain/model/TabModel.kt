package com.dindinn.app.domain.model

import com.google.gson.annotations.SerializedName

data class TabModel(
    @SerializedName("tab_count") val tabCount: Int?,
    @SerializedName("tabs") val tabs: List<TabDetails>
)


data class TabDetails(
    @SerializedName("category_id") val categoryId: Int?,
    @SerializedName("tab_title") val tabTitle: String?
)