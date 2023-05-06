package com.yashasvi.rehlat_assessment.repo

import com.yashasvi.rehlat_assessment.api.RetrofitInstance
import com.yashasvi.rehlat_assessment.models.Products
import retrofit2.Response

class ProductRepository() {

    suspend fun getProducts(): Response<Products> {
        return RetrofitInstance.api.getData()
    }


}