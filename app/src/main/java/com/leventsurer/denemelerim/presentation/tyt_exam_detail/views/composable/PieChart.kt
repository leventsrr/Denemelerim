package com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.himanshoe.charty.common.toChartDataCollection
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.model.PieData

@Composable

fun CustomPieChart(pieDataArrayList:ArrayList<PieData>) {
    PieChart(
        dataCollection = pieDataArrayList.toChartDataCollection(),
        modifier = Modifier.wrapContentSize()
    )
}