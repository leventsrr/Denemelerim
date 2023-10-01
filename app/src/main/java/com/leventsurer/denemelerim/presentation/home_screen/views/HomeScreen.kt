package com.leventsurer.denemelerim.presentation.home_screen.views

import android.annotation.SuppressLint
import android.view.Window
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.model.content.CircleShape
import com.google.android.material.bottomappbar.BottomAppBar
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.TytExamCard
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.common.database.DatabaseViewModel
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.AytExamCard
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
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
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
    val examTitles = arrayListOf("TYT Sınavlarım","AYT Sınavlarım")
    var state by remember { mutableStateOf(0) }

    Scaffold(
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
                if(state == 0){
                    LaunchedEffect(Unit ){
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetTytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                    }
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
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(databaseTytState.tytExams!!) { item ->
                                TytExamCard(item,navController)
                            }
                        }
                    }
                }else{

                    LaunchedEffect(Unit ){
                        databaseViewModel.onEvent(
                            DatabaseEvent.GetAytExams(
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )
                    }

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
                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
                            items(databaseAytState.aytExams!!) { item ->
                                AytExamCard(item)
                            }
                        }
                    }
                }
            }
        }
    )
}






