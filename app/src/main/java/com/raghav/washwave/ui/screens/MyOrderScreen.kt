package com.raghav.washwave.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.raghav.washwave.R
import com.raghav.washwave.domain.model.OrderHistoryItem
import com.raghav.washwave.ui.components.AppBar
import com.raghav.washwave.ui.components.FilterChips
import com.raghav.washwave.ui.components.OrderHistoryCard

@Composable
fun MyOrderScreen() {
    LazyColumn {
        item {
            AppBar(showLeadingIcon = false, title = "My Orders")
        }
        val filters = listOf("Cancelled", "Confirmed", "Pending","Completed")
       item{
           FilterChips(filters, null) {

           }
       }

        items(10) {
            OrderHistoryCard(orderHistoryItem = OrderHistoryItem(
                serviceName = "Ironing",
                orderNumber = "3248549",
                status = "Confirmed",
                imageRes = R.drawable.ic_wash
            )
            )
        }
    }
}