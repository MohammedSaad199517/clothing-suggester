package com.example.clothingsuggester.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.clothingsuggester.R


fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_baseline_cloud_download_24)
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(this)
}
