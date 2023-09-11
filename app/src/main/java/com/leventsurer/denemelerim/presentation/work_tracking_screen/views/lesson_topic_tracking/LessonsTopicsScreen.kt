package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.lesson_topic_tracking

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
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable.AytEqualWeightTopics
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable.AytNumericalTopics
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable.TytLessonsTopics

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



