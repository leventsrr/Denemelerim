package com.leventsurer.denemelerim.presentation.question_goal_screen.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.TytExamCard
import com.leventsurer.denemelerim.presentation.question_goal_screen.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.AytEqualWeightTopics
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.AytNumericalTopics
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.TytLessonsTopics
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent

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



