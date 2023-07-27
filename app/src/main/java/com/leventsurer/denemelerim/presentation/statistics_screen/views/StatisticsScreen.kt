package com.leventsurer.denemelerim.presentation.statistics_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.MyTopAppBar
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatisticsScreen(navController: NavController) {

    var chosenStatisticsType by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = { MyTopAppBar(appBarTitle = "İstatistiklerim", navController = navController) },
        content = {padding->
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                CustomSpinner(
                    listOfOptions = arrayListOf("TYT Net Bilgilerim", "AYT Net Bilgilerim"),
                    spinnerTitle = "Grafik Bilgisi"
                ) { statisticsType ->
                    chosenStatisticsType = statisticsType
                }
                //Puan bazlı
                LineChart()
                //Genel Net Bazlı
                LineChart()
                //Ders özelinde Net Bazlı
                LineChart()
            }
        }
    )

}





