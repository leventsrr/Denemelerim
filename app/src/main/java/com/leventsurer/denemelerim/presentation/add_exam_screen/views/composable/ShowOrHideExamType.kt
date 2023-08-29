package com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun ShowOrHideExamType(isVisible: Boolean, changeExamTypeVisibility: (Boolean) -> Unit) {
    Switch(
        checked = isVisible, onCheckedChange = changeExamTypeVisibility,
        colors = SwitchDefaults.colors(
            uncheckedTrackColor = Color.White,
            uncheckedBorderColor = Primary,
            checkedTrackColor = Secondary
        )
    )
}