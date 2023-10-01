package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TytLessonNetCardColumn(
    modifier: Modifier,
    firstLessonName: String,
    firstLessonPoint: String,
    secondLessonName: String,
    secondLessonPoint: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            Text(text = firstLessonName, modifier = Modifier.weight(1f))
            Text(text = firstLessonPoint, modifier = Modifier.weight(1f))

        }
        Row() {
            Text(text = secondLessonName, modifier = Modifier.weight(1f))
            Text(text = secondLessonPoint, modifier = Modifier.weight(1f))

        }

    }
}