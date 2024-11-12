package com.raghav.washwave.domain.model


data class LaundryServiceItem(
    val itemName: String,
    val serviceType: String,
    val price: Int,
    val currencySymbol: String = "₹"
)