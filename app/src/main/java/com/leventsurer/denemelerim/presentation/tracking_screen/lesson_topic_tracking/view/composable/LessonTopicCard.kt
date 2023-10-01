package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingEvent
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingViewModel
@Composable
fun LessonTopicCard(lessonName: String, lessonTopics: List<String>) {
    var isExtended by remember {
        mutableStateOf(false)
    }
    val userDoneTopics = remember { mutableListOf<String>() }
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
    val lessonTopicTrackingViewModel: LessonTopicTrackingViewModel = hiltViewModel()
    val lessonTopicTrackingViewModelState = lessonTopicTrackingViewModel.getUserExamLessonsTopicStatus.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
    LaunchedEffect(Unit ){
        lessonTopicTrackingViewModel.onEvent(
            LessonTopicTrackingEvent.GetExamLessonTopicStatus(
            dataStoreViewModel.getUserUidFromDataStore()
        ))
    }


    if(lessonTopicTrackingViewModelState.userExamLessonsTopicStatusResult != null){
        userDoneTopics.addAll(lessonTopicTrackingViewModelState.userExamLessonsTopicStatusResult)
    }

    Card(
        //border = BorderStroke(width = 1.dp, color = Primary),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            /*.animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing,
                )
            )*/
            .clickable {
                isExtended = !isExtended
                if(isExtended){
                    userDoneTopics.clear()
                    lessonTopicTrackingViewModel.onEvent(
                        LessonTopicTrackingEvent.GetExamLessonTopicStatus(
                            dataStoreViewModel.getUserUidFromDataStore()
                        ))
                }

            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = lessonName)
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "arrow")
            if (isExtended) {
                if(lessonTopicTrackingViewModelState.isLoading){
                    LottieAnimation(
                        modifier = Modifier.fillMaxWidth(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                }else{
                    for (topic in lessonTopics) {
                        if(topic in userDoneTopics){
                            TopicCard(topic,true)
                        }else{
                            TopicCard(topic,false)
                        }
                    }
                }


            }

        }
    }
}

