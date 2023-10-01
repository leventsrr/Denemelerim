package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun AytLessonNetCardRow(
    firstLessonName: String,
    firstLessonNet: Double,
    secondLessonName: String,
    secondLessonNet: Double
) {
    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = firstLessonName,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = ": ${String.format("%.2f", firstLessonNet)}",
                modifier = Modifier.weight(1f),
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = secondLessonName,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = ": ${String.format("%.2f", secondLessonNet)}",
                modifier = Modifier.weight(1f),
            )
        }
    }
}