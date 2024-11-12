package com.raghav.washwave.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.washwave.R
import com.raghav.washwave.domain.model.OrderHistoryItem

@Composable
fun OrderHistoryCard(orderHistoryItem: OrderHistoryItem) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            // Service Image
            Image(
                painter = painterResource(id = orderHistoryItem.imageRes),
                contentDescription = orderHistoryItem.serviceName,
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Service Details
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = orderHistoryItem.serviceName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Order No: #${orderHistoryItem.orderNumber}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Status Text
            Text(
                text = orderHistoryItem.status,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A90E2), // Light blue color for "Confirmed"
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewOrderHistoryCard() {
    val orderHistoryItem = OrderHistoryItem(
        serviceName = "Ironing",
        orderNumber = "3248549",
        status = "Confirmed",
        imageRes = R.drawable.ic_form
    )

    OrderHistoryCard(
        orderHistoryItem
    )
}
