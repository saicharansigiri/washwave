package com.raghav.washwave.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.washwave.domain.model.LaundryServiceItem
import com.raghav.washwave.ui.components.AppBar

@Composable
fun RatesScreen() {
    val laundryItems = listOf(
        LaundryServiceItem("Shirt / T-Shirt / Top", "Laundry", 99),
        LaundryServiceItem("Shirt / T-Shirt / Top", "Dry Cleaning", 149),
        LaundryServiceItem("Trouser / Jeans / Skirt / Frock / Slacks", "Laundry", 109),
        LaundryServiceItem("Trouser / Jeans / Skirt / Frock / Slacks", "Dry Cleaning", 160),
        LaundryServiceItem("Undergarment (M/F)", "Laundry", 49),
        LaundryServiceItem("Socks", "Laundry", 39),
        LaundryServiceItem("Hankey / Scarf", "Laundry", 29),
        LaundryServiceItem("Shorts", "Laundry", 60),
        LaundryServiceItem("Shorts", "Dry Cleaning", 80),
        LaundryServiceItem("Sarees", "Laundry", 180),
        LaundryServiceItem("Sarees", "Dry Cleaning", 220),
        LaundryServiceItem("Silk / Heavy Sarees", "Dry Cleaning", 299),
        LaundryServiceItem("Blouse", "Laundry", 55),
        LaundryServiceItem("Blouse", "Dry Cleaning", 70),
        LaundryServiceItem("Petty Coat", "Laundry", 50),
        LaundryServiceItem("Jerkin / Jacket / Coat", "Laundry", 220),
        LaundryServiceItem("Jerkin / Jacket / Coat", "Dry Cleaning", 290),
        LaundryServiceItem("Suit (2 pcs)", "Laundry", 350),
        LaundryServiceItem("Suit (2 pcs)", "Dry Cleaning", 499),
        LaundryServiceItem("Dhoti / Lungi", "Laundry", 80),
        LaundryServiceItem("Dhoti / Lungi", "Dry Cleaning", 160),
        LaundryServiceItem("Kurta / Kameez", "Laundry", 89),
        LaundryServiceItem("Kurta / Kameez", "Dry Cleaning", 120),
        LaundryServiceItem("Pajama / Salwar", "Laundry", 89),
        LaundryServiceItem("Pajama / Salwar", "Dry Cleaning", 120),
        LaundryServiceItem("Salwar Kameez (2 pcs)", "Laundry", 165),
        LaundryServiceItem("Salwar Kameez (2 pcs)", "Dry Cleaning", 220),
        LaundryServiceItem("Duppatta / Slip / Stockings", "Laundry", 49),
        LaundryServiceItem("Sweater / Cardigan / Pullover", "Laundry", 120),
        LaundryServiceItem("Sweater / Cardigan / Pullover", "Dry Cleaning", 190),
        LaundryServiceItem("Kidswear", "Laundry", 60),
        LaundryServiceItem("Kidswear", "Dry Cleaning", 80),
        LaundryServiceItem("Safari Coat (2 pcs)", "Laundry", 220),
        LaundryServiceItem("Safari Coat (2 pcs)", "Dry Cleaning", 299),
        LaundryServiceItem("Waist Coat", "Laundry", 149),
        LaundryServiceItem("Night Suit / Gown", "Laundry", 120),
        LaundryServiceItem("Night Suit / Gown", "Dry Cleaning", 145),
        LaundryServiceItem("Towel", "Laundry", 60),
        LaundryServiceItem("Track Suit", "Laundry", 120),
        LaundryServiceItem("Track Suit", "Dry Cleaning", 145),
        LaundryServiceItem("Turban", "Laundry", 80),
        LaundryServiceItem("Turban", "Dry Cleaning", 100),
        LaundryServiceItem("Tie", "Laundry", 60),
        LaundryServiceItem("Tie", "Dry Cleaning", 100),
        LaundryServiceItem("Swimming Suit", "Laundry", 120)
    )

    LaundryRatesList(laundryItems)
}

@Composable
fun LaundryRateCard(service: LaundryServiceItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = service.itemName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = service.serviceType,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "${service.currencySymbol}${service.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A90E2)
            )
        }
    }
}

@Composable
fun LaundryRatesList(services: List<LaundryServiceItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            AppBar(title = "Laundry Rates",showLeadingIcon = false)
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(services.size) { index ->
            LaundryRateCard(service = services[index])
        }
    }
}

@Preview
@Composable
fun PreviewLaundryRatesList() {
    val services = listOf(
        LaundryServiceItem(itemName = "Shirt", serviceType = "Wash & Iron", price = 20),
        LaundryServiceItem(itemName = "Trousers", serviceType = "Ironing", price = 10),
        LaundryServiceItem(itemName = "Saree", serviceType = "Dry Cleaning", price = 80),
        LaundryServiceItem(itemName = "Blouse", serviceType = "Darning", price = 15)
    )
    LaundryRatesList(services = services)
}