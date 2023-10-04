package com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.views.composable

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


@Composable
fun AytEqualWeightTopics() {
    val lessonTopicTrackingViewModel: LessonTopicTrackingViewModel = hiltViewModel()
    val lessonTopicTrackingViewModelState = lessonTopicTrackingViewModel.aytEqualWeightExamLessonsTopicState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))


    val mathTopicList = remember { mutableListOf<String>() }
    val geometryTopicList = remember { mutableListOf<String>() }
    val literatureTopicList = remember { mutableListOf<String>() }
    val geographyTopicList = remember { mutableListOf<String>() }
    val historyTopicList = remember { mutableListOf<String>() }

    LaunchedEffect(Unit) {
        lessonTopicTrackingViewModel.onEvent(
            LessonTopicTrackingEvent.GetAytEqualWeightExamLessonsTopic
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
    } else if (lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic != null) {

        mathTopicList.addAll(lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic.Matematik)
        geometryTopicList.addAll(lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic.Geometri)
        literatureTopicList.addAll(lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic.Edebiyat)
        geographyTopicList.addAll(lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic.Cografya)
        historyTopicList.addAll(lessonTopicTrackingViewModelState.aytEqualWeightExamLessonsTopic.Tarih1)



        LessonTopicCard("Matematik Konuları",mathTopicList)
        LessonTopicCard("Tarih Konuları",geometryTopicList)
        LessonTopicCard("Edebiyat Konuları",literatureTopicList)
        LessonTopicCard("Coğrafya Konuları",geographyTopicList)
        LessonTopicCard("Tarih Konuları",historyTopicList)



    }
}
