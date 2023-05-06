  package com.yashasvi.rehlat_assessment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yashasvi.rehlat_assessment.R
import com.yashasvi.rehlat_assessment.repo.ProductRepository
import com.yashasvi.rehlat_assessment.ui.viewmodels.ProductViewModel
import com.yashasvi.rehlat_assessment.ui.viewmodels.ProductViewModelProvideFactory

  class MainActivity : AppCompatActivity() {

      lateinit var viewModel:ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository =ProductRepository()
        val viewModelProvideFactory = ProductViewModelProvideFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProvideFactory).get(ProductViewModel::class.java)
    }
}