package com.leventsurer.denemelerim.presentation.home_screen.views

import android.annotation.SuppressLint
import android.view.Window
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.TytExamCard
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.common.database.DatabaseViewModel
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.AytExamCard
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navController: NavController
) {

    val databaseTytState = databaseViewModel.getTytExamsState.value
    val databaseAytState = databaseViewModel.getAytExamsState.value
    var chosenExamType by remember {
        mutableStateOf("")
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))




    Scaffold(
        //containerColor = Color.LightGray,
        bottomBar = {},
        topBar = {
            MyTopAppBar("Denemelerim", navController = navController)
        },
        floatingActionButton = {

            FloatingActionButton(
                containerColor = Secondary,
                onClick = { navController.navigate(Screen.AddExamScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Exam",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                   ElevatedButton(
                       border = BorderStroke(1.dp, Secondary),
                       onClick = {
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetTytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                        chosenExamType = "TYT"
                    }) {
                        Text(text = "TYT Sınavlarım", color = Secondary)
                    }
                    ElevatedButton(
                        border = BorderStroke(1.dp, Secondary),
                        onClick = {
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetAytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                        chosenExamType = "AYT"
                    }) {
                        Text(text = "AYT Sınavlarım", color = Secondary)
                    }
                }


                if (chosenExamType == "TYT") {
                    if (databaseTytState.isLoading) {
                        LottieAnimation(
                            modifier = Modifier.fillMaxWidth(),
                            composition = composition,
                            iterations = LottieConstants.IterateForever,
                        )

                    } else if (databaseTytState.tytExams?.size == 0) {
                        Text(
                            text = "Henüz Eklenmiş bir sınavın bulunmuyor.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        LazyColumn {
                            items(databaseTytState.tytExams!!) { item ->
                                TytExamCard(item)
                            }
                        }
                    }
                }
                else if (chosenExamType == "AYT") {
                    if (databaseAytState.isLoading) {
                        LottieAnimation(
                            modifier = Modifier.fillMaxWidth(),
                            composition = composition,
                            iterations = LottieConstants.IterateForever,
                        )

                    } else if (databaseAytState.aytExams?.size == 0) {
                        Text(
                            text = "Henüz Eklenmiş bir sınavın bulunmuyor.",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        LazyColumn {
                            items(databaseAytState.aytExams!!) { item ->
                                AytExamCard(item)
                            }
                        }
                    }
                }
                else{
                    Image(
                        painter = painterResource(id = R.drawable.home_page_image),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                            .padding(horizontal = 15.dp)
                    )
                }
            }


        }
    )
}





