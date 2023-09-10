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
fun AytEqualWeightTopics() {
    val goalQuestionViewModel: GoalQuestionViewModel = hiltViewModel()
    val goalQuestionViewModelState = goalQuestionViewModel.aytEqualWeightExamLessonsTopicState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))


    val mathTopicList = remember { mutableListOf<String>() }
    val geometryTopicList = remember { mutableListOf<String>() }
    val literatureTopicList = remember { mutableListOf<String>() }
    val geographyTopicList = remember { mutableListOf<String>() }
    val historyTopicList = remember { mutableListOf<String>() }

    LaunchedEffect(Unit) {
        goalQuestionViewModel.onEvent(
            QuestionGoalEvent.GetAytEqualWeightExamLessonsTopic
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
    } else if (goalQuestionViewModelState.aytEqualWeightExamLessonsTopic != null) {

        mathTopicList.addAll(goalQuestionViewModelState.aytEqualWeightExamLessonsTopic.Matematik)
        geometryTopicList.addAll(goalQuestionViewModelState.aytEqualWeightExamLessonsTopic.Geometri)
        literatureTopicList.addAll(goalQuestionViewModelState.aytEqualWeightExamLessonsTopic.Edebiyat)
        geographyTopicList.addAll(goalQuestionViewModelState.aytEqualWeightExamLessonsTopic.Cografya)
        historyTopicList.addAll(goalQuestionViewModelState.aytEqualWeightExamLessonsTopic.Tarih1)



        LessonTopicCard("Matematik Konuları",mathTopicList)
        LessonTopicCard("Tarih Konuları",geometryTopicList)
        LessonTopicCard("Edebiyat Konuları",literatureTopicList)
        LessonTopicCard("Coğrafya Konuları",geographyTopicList)
        LessonTopicCard("Tarih Konuları",historyTopicList)



    }
}
