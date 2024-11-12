package com.raghav.washwave.domain.model

data class OrderHistoryItem(
    val serviceName: String,
    val orderNumber: String,
    val status: String,
    val imageRes: Int
)

