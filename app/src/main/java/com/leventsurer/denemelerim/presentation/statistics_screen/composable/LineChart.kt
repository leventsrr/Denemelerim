package com.leventsurer.denemelerim.presentation.statistics_screen.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.AxisConfig
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.config.CurvedLineChartColors
import com.himanshoe.charty.line.config.LineConfig
import com.himanshoe.charty.line.model.LineData
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@Composable
fun LineChart(examNetList:ArrayList<Float>) {


    val lineDataList= ArrayList<LineData>()
    var index = 1
    for(line in examNetList){
        lineDataList.add(LineData(line,index))
        index++
    }

    val dataCollection = ChartDataCollection(
        lineDataList)

    val modifier = Modifier.height(200.dp).fillMaxWidth()
    val padding = 16.dp
    val axisConfig = AxisConfig(
        showAxes = true,
        showGridLines = true,
        showGridLabel = true,
        axisStroke = 3f,
        minLabelCount = 4,
        axisColor = Color.Black,
        gridColor = White
    )
    val radiusScale = 0.02f
    val lineConfig = LineConfig(
        hasSmoothCurve = true,
        hasDotMarker = true,
        strokeSize = 20f
    )
    val chartColors = CurvedLineChartColors(
        dotColor = listOf(Primary, Primary),
        backgroundColors = listOf(White, White),
        contentColor = listOf(LightGray, Gray)
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