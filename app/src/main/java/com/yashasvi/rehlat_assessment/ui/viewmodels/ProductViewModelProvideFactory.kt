package com.yashasvi.rehlat_assessment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yashasvi.rehlat_assessment.repo.ProductRepository

class ProductViewModelProvideFactory(val productRepo:ProductRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepo) as T
    }
}