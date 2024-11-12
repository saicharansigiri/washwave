package com.raghav.washwave.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.raghav.washwave.R
import com.raghav.washwave.domain.model.OrderHistoryItem
import com.raghav.washwave.domain.model.ServiceItem
import com.raghav.washwave.ui.components.AppBar
import com.raghav.washwave.ui.components.OrderHistoryCard
import com.raghav.washwave.ui.components.ServiceCard
import com.raghav.washwave.ui.navigation.CheckoutScreen
import com.raghav.washwave.ui.navigation.RateListScreen
import com.raghav.washwave.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewmodel: HomeViewModel, navHostController: NavHostController) {
    LazyColumn {
        item {
            AppBar(showLeadingIcon = false,title = "Wash Wave")
        }
        item{
            HomeScreenBody(){
                navHostController.navigate(CheckoutScreen)
            }
        }
    }
}

@Composable
fun HomeScreenBody(onServiceItemClick: () -> Unit = {}) {
    ServicesGrid(onServiceItemClick)
    ActiveOrders()
}


@Composable
fun ServicesGrid(onItemClick: () -> Unit = {}) {
    val services = listOf(
        ServiceItem("Wash & Iron", R.drawable.ic_wash),
        ServiceItem("Ironing", R.drawable.ic_iron),
        ServiceItem("Dry Cleaning", R.drawable.dry_clean_ic),
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Services",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        repeat(services.size) { index ->
            ServiceCard(service = services[index],onItemClick)
        }
    }
}

@Composable
fun ActiveOrders(){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Active Orders",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        repeat(3) { index ->
            OrderHistoryCard(orderHistoryItem = OrderHistoryItem(
                serviceName = "Ironing",
                orderNumber = "3248549",
                status = "Confirmed",
                imageRes = R.drawable.ic_wash
            ))
        }
    }
}