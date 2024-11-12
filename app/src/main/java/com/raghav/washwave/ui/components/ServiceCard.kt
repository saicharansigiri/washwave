package com.raghav.washwave.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.washwave.R
import com.raghav.washwave.domain.model.ServiceItem

@Composable
fun ServiceCard(service: ServiceItem,onItemClick: () -> Unit = {}) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth().clickable { onItemClick() },
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = service.icon),
                contentDescription = service.name,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = service.name,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4B4163) // Color similar to the one in screenshot
            )
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_chevrion_right),contentDescription = null)
            }
        }
    }
}

@Composable
@Preview
fun ServiceCardPreview() {
    val service = ServiceItem("Wash & Iron", R.drawable.ic_wash)
    ServiceCard(service = service)
}