package com.dindinn.app.data.source.remote

import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.domain.model.IngredientModel
import com.dindinn.app.domain.model.TabModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DinDinnService {

    @Headers("mocks:true")
    @GET("/api/orders")
    fun getOrders(): Single<DindinnOrder>

    @Headers("mocks:true")
    @GET("/api/ingredient_by_category/{category_id}")
    fun getIngredient(
        @Path("category_id") categoryId: Int
    ): Single<IngredientModel>

    @Headers("mocks:true")
    @GET("/api/ingredient_tabs")
    fun getIngredientTabs(): Single<TabModel>
}