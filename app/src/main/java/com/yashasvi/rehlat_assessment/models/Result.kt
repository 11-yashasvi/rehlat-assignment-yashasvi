package com.yashasvi.rehlat_assessment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductResult(
    val created_at: String,
    val image_ids: List<String>,
    val image_urls: List<String>,
    val image_urls_thumbnails: List<String>,
    val name: String,
    val price: String,
    val uid: String
)