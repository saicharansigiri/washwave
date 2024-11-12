package com.raghav.washwave.domain.model

data class LaundryItem(
    val name: String,
    val price: Int,
    val category: String,
    val icon: Int,  // Drawable resource ID for the icon
    var quantity: Int = 0
)
