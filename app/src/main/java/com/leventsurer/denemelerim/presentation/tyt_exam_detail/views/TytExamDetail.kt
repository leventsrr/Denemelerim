package com.leventsurer.denemelerim.presentation.tyt_exam_detail.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable.CustomPieChart
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TytExamDetail(
    navController:NavController
) {

    val cardModifier:Modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)

    val columnModifier:Modifier = Modifier.padding(5.dp)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Primary),
                title =
                {
                    Text("Sınav Detay", color = Color.White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                },
                navigationIcon = {
                        IconButton(
                            onClick = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                tint = Color.White
                            )

                        }

                }
            )
        },
        content = {
            Column(modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .verticalScroll(rememberScrollState())) {
                Card(modifier = cardModifier,colors = CardDefaults.cardColors(
                    Color.White
                ),elevation = CardDefaults.cardElevation(10.dp),) {
                    Column(modifier = columnModifier) {
                       Row(modifier = Modifier.fillMaxWidth()) {
                           Text(text = "Sınav Adı",modifier = Modifier.weight(1f))
                           Text(text = "Ad",modifier = Modifier.weight(1f))
                       }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Sınav Tarihi", modifier = Modifier.weight(1f))
                            Text(text = "Tarih",modifier = Modifier.weight(1f))
                        }
                    }
                }
                Card(
                    modifier = cardModifier,
                    elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )
                ) {
                    Column(modifier = columnModifier) {
                        Text(text = "Türkçe Sonuçları", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Doğru:35")
                            Text(text = "Yanlış:4")
                            Text(text = "Boş:1")
                            Text(text = "Net:34")
                        }
                    }
                }
                Card(
                    modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )
                ) {
                    Column(modifier = columnModifier) {
                        Text(text = "Sosyal Sonuçları",modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Column() {
                            Text(text = "Tarih")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                            Text(text = "Coğrafya")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                            Text(text = "Felsefe")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                            Text(text = "Din")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }

                        }

                    }
                }
                Card(
                    modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )
                ) {
                    Column(modifier = columnModifier) {
                        Text(text = "Matematik Sonuçları",modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Doğru:35")
                            Text(text = "Yanlış:4")
                            Text(text = "Boş:1")
                            Text(text = "Net:34")
                        }
                    }
                }
                Card(
                    modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        Color.White
                    )
                ) {
                    Column(modifier = columnModifier) {
                        Text(text = "Fen Sonuçları",modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Column() {
                            Text(text = "Fizik")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                            Text(text = "Kimya")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                            Text(text = "Biyoloji")
                            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                                Text(text = "Doğru:4")
                                Text(text = "Yanlış:1")
                                Text(text = "Boş:0")
                                Text(text = "Net:34")
                            }
                        }

                    }
                }
                Card(modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),colors = CardDefaults.cardColors(
                    Color.White
                )) {
                    Column(modifier = columnModifier) {
                        Text(text = "Genel Sınav Grafiği")
                        CustomPieChart()
                    }
                }
                Card(modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),colors = CardDefaults.cardColors(
                    Color.White
                )) {
                    Column(modifier = columnModifier) {
                        Text(text = "Genel Sınav Grafiği")
                        CustomPieChart()
                    }
                }
                Card(modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),colors = CardDefaults.cardColors(
                    Color.White
                )) {
                    Column(modifier = columnModifier) {
                        Text(text = "Genel Sınav Grafiği")
                        CustomPieChart()
                    }
                }
                Card(modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),colors = CardDefaults.cardColors(
                    Color.White
                )) {
                    Column(modifier = columnModifier) {
                        Text(text = "Genel Sınav Grafiği")
                        CustomPieChart()
                    }
                }


            }
        }
    )

}