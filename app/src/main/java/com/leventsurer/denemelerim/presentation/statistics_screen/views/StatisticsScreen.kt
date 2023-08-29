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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatisticsScreen(navController: NavController) {

    val examTitles = arrayListOf("TYT Net Bilgilerim","AYT Net Bilgilerim")
    var state by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { MyTopAppBar(appBarTitle = "İstatistiklerim", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                TabRow(selectedTabIndex = state) {
                    examTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = state == index,
                            onClick = { state = index },
                            text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                            selectedContentColor = Secondary,
                            unselectedContentColor = Primary
                        )
                    }
                }
                if(state==0){
                    TytStatisticsScreen()
                }else{
                    AytStatisticsScreen()
                }
            }
        }
    )

}





