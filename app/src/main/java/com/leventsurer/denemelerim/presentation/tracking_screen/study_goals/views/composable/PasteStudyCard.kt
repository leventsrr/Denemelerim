package com.leventsurer.denemelerim.presentation.tracking_screen.study_goals.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun PastStudyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(5.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "#DenemeSınavıAnaliz")
                Text(text = "04.10.2023")
            }
            Divider()
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Çalışma Süresi ")
                Text(text = "1 saat 45 dakika")
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Çalışma Verim Puanı ")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "5")
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star", tint = Secondary, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}