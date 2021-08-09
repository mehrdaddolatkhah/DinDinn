package com.dindinn.app.domain.model

import androidx.lifecycle.MutableLiveData
import com.dindinn.app.util.Utils.toSortTime
import com.google.gson.annotations.SerializedName

data class DindinnOrder(
    @SerializedName("status") val status: StatusDetails?,
    @SerializedName("data") val data: List<OrderDataDetails>?
)

data class StatusDetails(
    @SerializedName("success") val success: Boolean?,
    @SerializedName("statusCode") val statusCode: Int?,
    @SerializedName("message") val message: String?,
)

data class OrderDataDetails(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("addon") val orderAddOn: List<OrderAddOn>?,
    @SerializedName("quantity") val quantity: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("alerted_at") val alertedAt: String?,
    @SerializedName("expired_at") val expiredAt: String?,

    val orderCountDown: MutableLiveData<String>?
) {
    fun makeTimeShortForm(): String {
        return createdAt?.toSortTime() ?: ""
    }
}


data class OrderAddOn(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("quantity") val quantity: Int?,
)

