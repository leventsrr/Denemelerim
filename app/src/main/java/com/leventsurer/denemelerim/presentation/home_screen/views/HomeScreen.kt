package com.leventsurer.denemelerim.presentation.home_screen.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.TytExamCard
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.common.database.DatabaseViewModel
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.AytExamCard
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor

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



    Scaffold(
        containerColor = Color.LightGray,
        bottomBar = {},
        topBar = {
            MyTopAppBar("Denemelerim", navController = navController)
        },
        floatingActionButton = {

            FloatingActionButton(
                containerColor = PrimaryColor,
                onClick = { navController.navigate(Screen.AddExamScreen.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Exam")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    ElevatedButton(onClick = {
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetTytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                        chosenExamType = "TYT"
                    }) {
                        Text(text = "TYT Sınavlarım")
                    }
                    ElevatedButton(onClick = {
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetAytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                        chosenExamType = "AYT"
                    }) {
                        Text(text = "AYT Sınavlarım")
                    }
                }

                if (chosenExamType == "TYT"){
                    if (databaseTytState.isLoading ) {
                        CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))

                    } else if (databaseTytState.tytExams?.size == 0) {
                        Text(text = "Henüz Eklenmiş bir sınavın bulunmuyor.",textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    } else {


                        LazyColumn(

                        ) {
                            items(databaseTytState.tytExams!!) { item ->
                                TytExamCard(item, navController = navController)
                            }
                        }
                    }
                }else if(chosenExamType == "AYT"){
                    if (databaseAytState.isLoading ) {
                        CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))

                    } else if (databaseAytState.aytExams?.size == 0) {
                        Text(text = "Henüz Eklenmiş bir sınavın bulunmuyor.", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())
                    } else {


                        LazyColumn(

                        ) {
                            items(databaseAytState.aytExams!!) { item ->
                                AytExamCard(item, navController = navController)
                            }
                        }
                    }
                }

            }


        }
    )
}





