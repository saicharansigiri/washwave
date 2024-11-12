package com.raghav.washwave.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.raghav.washwave.R

@Composable
fun AppBar(title: String = "Wash Wave",showLeadingIcon: Boolean = true) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
       if(showLeadingIcon){
           IconButton(onClick = { }) {
               Icon(
                   painter = painterResource(R.drawable.baseline_keyboard_backspace_24),
                   contentDescription = "Back"
               )
           }
       }

        Text(text = title, modifier = Modifier.weight(1f), textAlign = TextAlign.Center,style = androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        ))

       if(showLeadingIcon){
           IconButton(onClick = { },modifier = Modifier.alpha(0f)) {
               Icon(
                   painter = painterResource(R.drawable.baseline_keyboard_backspace_24),
                   contentDescription = "Back"
               )
           }
       }

    }
}
