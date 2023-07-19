package com.leventsurer.denemelerim.presentation.add_exam_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.MyTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExamScreen(navController: NavController) {
    var chosenExamType by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
                 MyTopAppBar("Sınav Ekle")
        },
        content = {padding->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Top,) {
                    CustomSpinner { examType ->
                        chosenExamType = examType
                    }
                when (chosenExamType) {
                    "" -> {
                        Text(text = "Lütfen Sınav Tipini Seçiniz", fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                    "TYT" -> {
                        ScreenForTyt()
                    }
                    "AYT" -> {
                        ScreenForAyt()
                    }
                }

            }       
        },
    )

}


