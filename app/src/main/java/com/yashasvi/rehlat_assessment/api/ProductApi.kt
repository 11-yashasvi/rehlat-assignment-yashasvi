package com.yashasvi.rehlat_assessment.api

import com.yashasvi.rehlat_assessment.models.Products
import com.yashasvi.rehlat_assessment.utils.MyUtils
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("${MyUtils.BASE_URL}")
    suspend fun getData(): Response<Products>
}