package com.yashasvi.rehlat_assessment.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yashasvi.rehlat_assessment.models.ProductResult
import com.yashasvi.rehlat_assessment.models.Products
import com.yashasvi.rehlat_assessment.repo.ProductRepository
import com.yashasvi.rehlat_assessment.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(val productRepo: ProductRepository) :ViewModel() {

    val productData : MutableLiveData<Resource<Products>> = MutableLiveData()
    var prevData :List<ProductResult> = listOf()

    init {
        getProducts()
    }

    fun getProducts()= viewModelScope.launch {
        productData.postValue(Resource.Loading())
        val response = productRepo.getProducts()
        prevData = handleProductReponse(response).data!!.results
        productData.postValue(handleProductReponse(response))
    }

    private fun handleProductReponse(response: Response<Products>):Resource<Products>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun sortByDate(){
        var mlist = productData.value?.data?.results
        mlist = mlist?.sortedBy { it.created_at}
        productData.postValue(Resource.Success(Products(results = mlist!!, pagination = null)))
    }
    fun sortByPrice(){
        var mlist = productData.value?.data?.results
        mlist = mlist?.sortedBy { it.price.substring(4).toInt()}
        productData.postValue(Resource.Success(Products(results = mlist!!, pagination = null)))
    }
    fun getNormalDate(){

        productData.postValue(Resource.Success(Products(results = prevData!!, pagination = null)))
    }
}