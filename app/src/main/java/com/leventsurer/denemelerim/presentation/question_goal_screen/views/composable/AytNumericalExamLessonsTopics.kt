package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.question_goal_screen.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.QuestionGoalEvent

@Composable
fun AytNumericalTopics() {
    val goalQuestionViewModel: GoalQuestionViewModel = hiltViewModel()
    val goalQuestionViewModelState = goalQuestionViewModel.aytNumericalExamLessonsTopicState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))


    val mathTopicList = remember { mutableListOf<String>() }
    val geometryTopicList = remember { mutableListOf<String>() }
    val physicsTopicList = remember { mutableListOf<String>() }
    val chemicalTopicList = remember { mutableListOf<String>() }
    val biologyTopicList = remember { mutableListOf<String>() }

    LaunchedEffect(Unit) {
        goalQuestionViewModel.onEvent(
            QuestionGoalEvent.GetAytNumericalExamLessonsTopic
        )

    }


    if (goalQuestionViewModelState.isLoading) {
        LottieAnimation(
            modifier = Modifier.fillMaxWidth(),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    } else if (goalQuestionViewModelState.error != null) {
        Text(text = goalQuestionViewModelState.error)
    } else if (goalQuestionViewModelState.aytNumericalExamLessonsTopic != null) {

        mathTopicList.addAll(goalQuestionViewModelState.aytNumericalExamLessonsTopic.Matematik)
        geometryTopicList.addAll(goalQuestionViewModelState.aytNumericalExamLessonsTopic.Geometri)
        physicsTopicList.addAll(goalQuestionViewModelState.aytNumericalExamLessonsTopic.Fizik)
        biologyTopicList.addAll(goalQuestionViewModelState.aytNumericalExamLessonsTopic.Biyoloji)
        chemicalTopicList.addAll(goalQuestionViewModelState.aytNumericalExamLessonsTopic.Kimya)

        LessonTopicCard("Matematik Konuları",mathTopicList)
        LessonTopicCard("Tarih Konuları",geometryTopicList)
        LessonTopicCard("Fizik Konuları",physicsTopicList)
        LessonTopicCard("Kimya Konuları",chemicalTopicList)
        LessonTopicCard("Biyoloji Konuları",biologyTopicList)



    }




}