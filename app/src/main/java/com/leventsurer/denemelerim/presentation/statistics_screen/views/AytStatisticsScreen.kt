package com.leventsurer.denemelerim.presentation.statistics_screen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart

@Composable
fun AytStatisticsScreen() {

    var isNumericalStatisticsChecked by remember {
        mutableStateOf(false)
    }
    var isEqualWeightStatisticsChecked by remember {
        mutableStateOf(false)
    }
    var isVerbalStatisticsChecked by remember {
        mutableStateOf(false)
    }

    Column() {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sayısal İstatistikleri")
            Checkbox(checked = isNumericalStatisticsChecked, onCheckedChange = {isNumericalStatisticsChecked = it})

        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Eşit Ağırlık İstatistikleri")

            Checkbox(checked = isEqualWeightStatisticsChecked, onCheckedChange = {isEqualWeightStatisticsChecked = it})
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sözel İstatistikleri")

            Checkbox(checked = isVerbalStatisticsChecked, onCheckedChange = {isVerbalStatisticsChecked = it})
        }

        if(isNumericalStatisticsChecked){
            Column {
                Text(text = "Sayısal İstatistikler")
                Text(text = "Genel Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Matematik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Fizik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Kimya Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Biyoloji Toplam Net")
                LineChart(arrayListOf(1f,2f))
            }
        }
        if(isEqualWeightStatisticsChecked){
            Column {
                Text(text = "Eşit Ağırlık İstatistikler")
                Text(text = "Genel Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Matematik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Fizik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Kimya Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Biyoloji Toplam Net")
                LineChart(arrayListOf(1f,2f))
            }
        }
        if(isVerbalStatisticsChecked){
            Column {
                Text(text = "Sözel İstatistikler")
                Text(text = "Genel Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Matematik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Fizik Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Kimya Toplam Net")
                LineChart(arrayListOf(1f,2f))
                Text(text = "Biyoloji Toplam Net")
                LineChart(arrayListOf(1f,2f))
            }
        }


    }
}