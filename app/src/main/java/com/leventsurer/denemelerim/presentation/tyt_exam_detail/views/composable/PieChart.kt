package com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.charty.common.toChartDataCollection
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.model.PieData

@Composable

fun CustomPieChart() {
    val data = listOf(
        PieData(30f, "Category A", Color.Blue),
        PieData(20f, "Category B", Color.Red),
        PieData(10f, "Category C", Color.Green),
        PieData(10f, "Category C", Color.Black),
    )
    PieChart(
        dataCollection = data.toChartDataCollection(),
        modifier = Modifier.wrapContentSize()
    )
}