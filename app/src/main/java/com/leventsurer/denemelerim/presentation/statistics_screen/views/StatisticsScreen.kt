package com.leventsurer.denemelerim.presentation.statistics_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatisticsScreen(navController: NavController) {

    var chosenStatisticsType by remember {
        mutableStateOf("")
    }


    Scaffold(
        topBar = { MyTopAppBar(appBarTitle = "İstatistiklerim", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                CustomSpinner(
                    listOfOptions = arrayListOf("TYT Net Bilgilerim", "AYT Net Bilgilerim"),
                    spinnerTitle = "Grafik Bilgisi"
                ) { statisticsType ->
                    chosenStatisticsType = statisticsType
                }
                when (chosenStatisticsType) {
                    "TYT Net Bilgilerim" -> {
                        TytStatisticsScreen()
                    }
                    "AYT Net Bilgilerim" -> {
                        AytStatisticsScreen()
                    }
                    else -> {
                        Image(
                            painter = painterResource(id = R.drawable.charts_image),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                                .padding(horizontal = 15.dp)
                        )
                    }
                }
            }
        }
    )

}





