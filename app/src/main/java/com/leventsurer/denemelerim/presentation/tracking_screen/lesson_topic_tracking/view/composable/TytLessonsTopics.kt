package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.composable

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
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalEvent


@Composable
fun TytLessonsTopics() {

    val lessonTopicTrackingViewModel: LessonTopicTrackingViewModel = hiltViewModel()
    val lessonTopicTrackingViewModelState = lessonTopicTrackingViewModel.tytExamLessonsTopicState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))

    val biologyTopicList = remember { mutableListOf<String>() }
    val physicsTopicList = remember { mutableListOf<String>() }
    val chemicalTopicList = remember { mutableListOf<String>() }
    val mathTopicList = remember { mutableListOf<String>() }
    val turkishTopicList = remember { mutableListOf<String>() }
    val philosophyTopicList = remember { mutableListOf<String>() }
    val historyTopicList = remember { mutableListOf<String>() }
    val religionTopicList = remember { mutableListOf<String>() }
    val geographyTopicList = remember { mutableListOf<String>() }


    LaunchedEffect(Unit) {
        lessonTopicTrackingViewModel.onEvent(
            LessonTopicTrackingEvent.GetTytLessonsTopics
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
    } else if (lessonTopicTrackingViewModelState.tytTopicsResult != null) {

        mathTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Matematik)
        turkishTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Turkce)
        physicsTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Fizik)
        biologyTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Biyoloji)
        chemicalTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Kimya)
        historyTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Tarih)
        geographyTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Cografya)
        religionTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.DinKulturu)
        philosophyTopicList.addAll(lessonTopicTrackingViewModelState.tytTopicsResult.Felsefe)


        LessonTopicCard("Türkçe Konuları",turkishTopicList)
        LessonTopicCard("Matematik Konuları",mathTopicList)
        LessonTopicCard("Tarih Konuları",historyTopicList)
        LessonTopicCard("Coğrafya Konuları",geographyTopicList)
        LessonTopicCard("Felsefe Konuları",philosophyTopicList)
        LessonTopicCard("Din Kültürü Konuları",religionTopicList)
        LessonTopicCard("Fizik Konuları",physicsTopicList)
        LessonTopicCard("Kimya Konuları",chemicalTopicList)
        LessonTopicCard("Biyoloji Konuları",biologyTopicList)



    }


}
