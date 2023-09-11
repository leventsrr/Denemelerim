package com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.view.composable

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
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingEvent
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.composable.LessonTopicCard
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalEvent

@Composable
fun AytNumericalTopics() {
    val lessonTopicTrackingViewModel: LessonTopicTrackingViewModel = hiltViewModel()
    val lessonTopicTrackingViewModelState = lessonTopicTrackingViewModel.aytNumericalExamLessonsTopicState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))


    val mathTopicList = remember { mutableListOf<String>() }
    val geometryTopicList = remember { mutableListOf<String>() }
    val physicsTopicList = remember { mutableListOf<String>() }
    val chemicalTopicList = remember { mutableListOf<String>() }
    val biologyTopicList = remember { mutableListOf<String>() }

    LaunchedEffect(Unit) {
        lessonTopicTrackingViewModel.onEvent(
            LessonTopicTrackingEvent.GetAytNumericalExamLessonsTopic
        )

    }


    if (lessonTopicTrackingViewModelState.isLoading) {
        LottieAnimation(
            modifier = Modifier.fillMaxWidth(),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    } else if (lessonTopicTrackingViewModelState.error != null) {
        Text(text = lessonTopicTrackingViewModelState.error)
    } else if (lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic != null) {

        mathTopicList.addAll(lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic.Matematik)
        geometryTopicList.addAll(lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic.Geometri)
        physicsTopicList.addAll(lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic.Fizik)
        biologyTopicList.addAll(lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic.Biyoloji)
        chemicalTopicList.addAll(lessonTopicTrackingViewModelState.aytNumericalExamLessonsTopic.Kimya)

        LessonTopicCard("Matematik Konuları",mathTopicList)
        LessonTopicCard("Tarih Konuları",geometryTopicList)
        LessonTopicCard("Fizik Konuları",physicsTopicList)
        LessonTopicCard("Kimya Konuları",chemicalTopicList)
        LessonTopicCard("Biyoloji Konuları",biologyTopicList)



    }




}