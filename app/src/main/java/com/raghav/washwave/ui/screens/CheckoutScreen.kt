package com.raghav.washwave.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raghav.washwave.R
import com.raghav.washwave.domain.model.LaundryItem
import com.raghav.washwave.domain.model.LaundryServiceItem

@Composable
fun CheckoutScreen() {

    val laundryItems = listOf(
        LaundryItem("T-Shirt", 2, "Men", R.drawable.ic_iron),
        LaundryItem("Outer Wear", 2, "Men", R.drawable.ic_iron),
        LaundryItem("Bottom", 2, "Men", R.drawable.ic_iron),
        LaundryItem("Dresses", 2, "Women", R.drawable.ic_iron),
        LaundryItem("Home", 2, "Home", R.drawable.ic_home),
        LaundryItem("Other", 2, "Other", R.drawable.ic_iron)
    )

    LaundryItemList(laundryItems)
}


@Composable
fun LaundryItemCard(
    item: LaundryItem,
    onQuantityChange: (LaundryItem, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icon and details
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = item.name,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "$${item.price}",
                    color = Color.Red,
                    fontSize = 14.sp
                )
                Text(
                    text = item.category,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // Quantity selector
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (item.quantity > 0) onQuantityChange(item, item.quantity - 1) },
                enabled = item.quantity > 0
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_minus),
                    contentDescription = "Decrease quantity"
                )
            }
            Text(
                text = item.quantity.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            IconButton(
                onClick = { onQuantityChange(item, item.quantity + 1) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase quantity"
                )
            }
        }
    }
}

@Composable
fun LaundryItemList(items: List<LaundryItem>) {
    val updatedItems = remember { items.toMutableStateList() }

    Column {
        updatedItems.forEach { item ->
            LaundryItemCard(item = item) { updatedItem, newQuantity ->
                val index = updatedItems.indexOf(updatedItem)
                if (index != -1) {
                    updatedItems[index] = updatedItems[index].copy(quantity = newQuantity)
                }
            }
        }
    }
}

