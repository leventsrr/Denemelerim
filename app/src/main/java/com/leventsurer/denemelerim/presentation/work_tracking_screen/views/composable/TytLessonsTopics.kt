package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable

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
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.lesson_topic_tracking.composable.LessonTopicCard
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals.QuestionGoalEvent


@Composable
fun TytLessonsTopics() {

    val goalQuestionViewModel: GoalQuestionViewModel = hiltViewModel()
    val goalQuestionViewModelState = goalQuestionViewModel.tytExamLessonsTopicState.value
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
        goalQuestionViewModel.onEvent(
            QuestionGoalEvent.GetTytLessonsTopics
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
    } else if (goalQuestionViewModelState.tytTopicsResult != null) {

        mathTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Matematik)
        turkishTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Turkce)
        physicsTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Fizik)
        biologyTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Biyoloji)
        chemicalTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Kimya)
        historyTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Tarih)
        geographyTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Cografya)
        religionTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.DinKulturu)
        philosophyTopicList.addAll(goalQuestionViewModelState.tytTopicsResult.Felsefe)


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
