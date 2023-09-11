package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.presentation.ui.theme.Green700
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@Composable
fun CustomLinearProgressIndicator(
    progress: Float,
    progressColor: Color = Green700,
    backgroundColor: Color = Primary,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = Modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}