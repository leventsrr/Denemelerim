package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.view.composable.AytEqualWeightTopics
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.view.composable.AytNumericalTopics
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.composable.TytLessonsTopics

@Composable
fun LessonsTopicScreen() {

    val spinnerExamTitles =
        arrayListOf("TYT Konuları", "AYT Sayısal Konuları", "AYT Eşit Ağırlık Konuları")
    var selectedType by remember {
        mutableStateOf("")
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        CustomSpinner(spinnerTitle = "Sınav Tipi", listOfOptions = spinnerExamTitles, onClick = {
            selectedType = it
        })

        when (selectedType) {
            "TYT Konuları" -> {
                TytLessonsTopics()
            }

            "AYT Sayısal Konuları" -> {
                AytNumericalTopics()
            }

            "AYT Eşit Ağırlık Konuları" -> {
                AytEqualWeightTopics()
            }
        }


    }
}



