package com.leventsurer.denemelerim.presentation.statistics_screen.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.AxisConfig
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.config.CurvedLineChartColors
import com.himanshoe.charty.line.config.LineConfig
import com.himanshoe.charty.line.model.LineData

@Composable
fun LineChart() {
    val dataCollection = ChartDataCollection(
        listOf(
            LineData(1f,"1"),
            LineData(3f,"2"),
            LineData(5f,"3"),
            LineData(7f,"4"),
            LineData(2f,"5"),
            LineData(2f,"6"),
            LineData(7f,"7"),
            LineData(10f,"8"),
        ))

    val modifier = Modifier.height(200.dp).fillMaxWidth()
    val padding = 16.dp
    val axisConfig = AxisConfig(
        showAxes = true,
        showGridLines = true,
        showGridLabel = true,
        axisStroke = 2f,
        minLabelCount = 4,
        axisColor = Color.Black,
        gridColor = Color.LightGray
    )
    val radiusScale = 0.02f
    val lineConfig = LineConfig(
        hasSmoothCurve = true,
        hasDotMarker = true,
        strokeSize = 2f
    )
    val chartColors = CurvedLineChartColors(
        dotColor = listOf(Color.Yellow, Color.Yellow),
        backgroundColors = listOf(Color.Blue, Color.Black),
        contentColor = listOf(Color.White, Color.White)
    )

    CurveLineChart(
        dataCollection = dataCollection,
        modifier = modifier,
        padding = padding,
        axisConfig = axisConfig,
        radiusScale = radiusScale,
        lineConfig = lineConfig,
        chartColors = chartColors
    )

}