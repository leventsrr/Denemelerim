package com.leventsurer.denemelerim.presentation.question_goal_screen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner

@Composable
fun LessonsTopicScreen() {

    val spinnerExamTitles  = arrayListOf("TYT Konuları", "AYT Sayısal Konuları","AYT Eşit Ağırlık Konuları")
    Column(modifier = Modifier.fillMaxSize()) {

        CustomSpinner(spinnerTitle = "Sınav Tipi", listOfOptions =spinnerExamTitles , onClick ={

        } )
    }
}