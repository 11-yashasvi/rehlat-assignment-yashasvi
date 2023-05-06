package com.androiddevs.mvvmnewsapp.adapters

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashasvi.rehlat_assessment.R
import com.yashasvi.rehlat_assessment.models.ProductResult
import com.yashasvi.rehlat_assessment.ui.fragments.DetailsFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {



    private val differCallback = object : DiffUtil.ItemCallback<ProductResult>() {
        override fun areItemsTheSame(oldItem: ProductResult, newItem: ProductResult): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: ProductResult, newItem: ProductResult): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((ProductResult) -> Unit)? = null
    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productImage: ImageView =itemView.findViewById(R.id.ivProductImage)
        val textCreated: TextView = itemView.findViewById(R.id.tvCreated)
        val txtName: TextView = itemView.findViewById(R.id.tvName)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val Product = differ.currentList[position]
            Glide.with(holder.itemView).load(Product.image_urls_thumbnails[0]).into(holder.productImage)
            holder.txtName.text = "Name: " + Product.name
            holder.price.text = "Price: " + Product.price
            holder.textCreated.text = "Created at: "+ convertDateTime(Product.created_at)
            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("image", Product.image_urls[0])
                bundle.putString("name", Product.name)
                bundle.putString("price", Product.price)
                bundle.putString("createdat", Product.created_at)
                var blueFragment:DetailsFragment = DetailsFragment()
                blueFragment.arguments = bundle
                it.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment,bundle)
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












