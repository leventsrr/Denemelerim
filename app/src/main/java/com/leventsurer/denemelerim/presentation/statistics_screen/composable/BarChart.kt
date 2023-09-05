package com.leventsurer.denemelerim.presentation.statistics_screen.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.model.BarData
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.AxisConfig
import com.himanshoe.charty.common.config.ChartDefaults
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun MyBarChart(
    examNetList: ArrayList<Float>,
) {
    val barData = arrayListOf<BarData>()
    for (i in 0 until examNetList.size) {
        barData.add(BarData(examNetList[i], (i+1).toString(), color = Color(Secondary.toArgb())))
    }
    val modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
    val barSpacing = 8.dp
    val padding: Dp = 16.dp
    val barColor: Color = Color.Blue
    val axisConfig: AxisConfig = ChartDefaults.axisConfigDefaults()


    BarChart(ChartDataCollection(barData), modifier, barSpacing, padding, /*barColor,*/ axisConfig)
}