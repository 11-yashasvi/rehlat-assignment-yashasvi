package com.yashasvi.rehlat_assessment.ui.fragments

import android.content.Intent.getIntent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.yashasvi.rehlat_assessment.R
import com.yashasvi.rehlat_assessment.ui.MainActivity
import com.yashasvi.rehlat_assessment.ui.viewmodels.ProductViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var viewModel: ProductViewModel
    lateinit var iv:ImageView
    lateinit var tvName:TextView
    lateinit var tvPrice:TextView
    lateinit var tvCreatedAt:TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel


        val bundle = arguments

        var image:String= ""
        var name:String= ""
        var createdat:String= ""
        var price:String= ""
        tvPrice = (activity as MainActivity).findViewById(R.id.product_price)
        tvCreatedAt = (activity as MainActivity).findViewById(R.id.product_created_at)
        tvName = (activity as MainActivity).findViewById(R.id.product_name)
        iv =  (activity as MainActivity).findViewById(R.id.product_image)
        if(bundle!=null){
            image = bundle!!.getString("image", "")
            name = bundle!!.getString("name", "")
            price = bundle!!.getString("price", "0")
            createdat = bundle!!.getString("createdat", "")
            tvCreatedAt.text = convertDateTime(createdat)
            tvName.text = name
            tvPrice.text = price
            Glide.with(activity as MainActivity).load(image).placeholder(R.drawable.baseline_insert_photo_24).into(iv)

        }
    }

    fun convertDateTime(dateTimeStr: String): String {
        val inputFormatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, dd MMMM yyyy")
        val dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter)
        return dateTime.format(outputFormatter)
    }

}