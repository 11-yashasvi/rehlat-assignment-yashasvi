package com.yashasvi.rehlat_assessment.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.adapters.ProductsAdapter
import com.yashasvi.rehlat_assessment.R
import com.yashasvi.rehlat_assessment.ui.MainActivity
import com.yashasvi.rehlat_assessment.ui.viewmodels.ProductViewModel
import com.yashasvi.rehlat_assessment.utils.Resource


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var viewModel:ProductViewModel
    lateinit var padapter: ProductsAdapter
    lateinit var review:RecyclerView
    lateinit var progressBar:ProgressBar
    lateinit var date:ImageButton
    lateinit var price:ImageButton
    var datebool =false
    var pricebool =false
    val TAG = "PRODUCT FRAGMENT"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        review = (activity as MainActivity).findViewById(R.id.rvProductView)
        progressBar = (activity as MainActivity).findViewById(R.id.pProgressBar)
        date = (activity as MainActivity).findViewById(R.id.date)
        price = (activity as MainActivity).findViewById(R.id.price)

        setUpRecyclerView()
        date.setOnClickListener{
            datebool=!datebool
            if(datebool){
                Toast.makeText(activity,"Sorted by Date & Time",Toast.LENGTH_SHORT).show()
                date.setImageResource(R.drawable.baseline_date_range)
                viewModel.sortByDate()
            }
            else{
                date.setImageResource(R.drawable.baseline_date_range_24)
                viewModel.getNormalDate()
            }
        }
        price.setOnClickListener {
            pricebool=!pricebool
            if(pricebool){
                Toast.makeText(activity,"Sorted by Price",Toast.LENGTH_SHORT).show()
                price.setImageResource(R.drawable.baseline_price_change)
                viewModel.sortByPrice()
            }
            else{
                price.setImageResource(R.drawable.baseline_price_change_24)
                viewModel.getNormalDate()
            }
        }

        viewModel.productData.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data.let {productResponse->
                        var mlist = productResponse?.results
                        padapter.differ.submitList(mlist)
                        padapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {message->
                        Log.e(TAG,"An error occured : $message")

                    }
                }
                is Resource.Loading->{
                   showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        progressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
    private fun setUpRecyclerView(){
        padapter = ProductsAdapter()
        review.apply {
            adapter = padapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}