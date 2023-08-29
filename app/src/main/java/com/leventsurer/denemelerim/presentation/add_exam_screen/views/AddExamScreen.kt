package com.leventsurer.denemelerim.presentation.add_exam_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddExamScreen(navController: NavController) {
    var chosenExamType by remember {
        mutableStateOf("")
    }

    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }
    var examName by remember {
        mutableStateOf("")
    }

    var aboutExam by remember {
        mutableStateOf("")
    }
    val examTitles = arrayListOf("TYT Sınavı Ekle","AYT Sınavı Ekle")
    var examTypeState by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            MyTopAppBar("Sınav Ekle", navController = navController)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
            ) {

                TabRow(selectedTabIndex = examTypeState) {
                    examTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = examTypeState == index,
                            onClick = { examTypeState = index },
                            text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                            selectedContentColor = Secondary,
                            unselectedContentColor = Primary
                        )
                    }
                }
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    value = examName,
                    onValueChange = {
                        examName = it
                    },
                    label = { Text(text = "Deneme Adı") },
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    value = aboutExam,
                    onValueChange = {
                        aboutExam = it
                    },
                    label = { Text(text = "Deneme Hakkında") },
                )
                if (examTypeState == 0){
                    ScreenForTyt(examName, aboutExam)
                }else{
                    ScreenForAyt(examName, aboutExam)
                }
            }
        },
    )
}