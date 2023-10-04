package com.leventsurer.denemelerim.presentation.leaderboard_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LeaderBoardCardPointRow(pointType:String, point:Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = pointType, fontWeight = FontWeight.Bold)
        Text(text = String.format("%.3f",point))
    }
}