package com.leventsurer.denemelerim.presentation.add_exam_screen.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.presentation.add_exam_screen.AddExamEvent
import com.leventsurer.denemelerim.presentation.add_exam_screen.AddExamViewModel
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable.LessonCorrectAndFalseInputs
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingEvent
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.LessonTopicTrackingViewModel
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable.LessonFalseTopics
import com.leventsurer.denemelerim.presentation.ui.theme.Primary


@Composable
fun ScreenForTyt(
    examName: String,
    aboutExam: String,
    addExamViewModel: AddExamViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    lessonTopicTrackingViewModel: LessonTopicTrackingViewModel = hiltViewModel()
) {
    
    var isExamNameNullOrEmpty by remember {
        mutableStateOf(false)
    }
    var turkishCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var turkishFalse by rememberSaveable {
        mutableStateOf("")
    }
    var socialCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var socialFalse by rememberSaveable {
        mutableStateOf("")
    }
    var mathCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var mathFalse by rememberSaveable {
        mutableStateOf("")
    }
    var scienceCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var scienceFalse by rememberSaveable {
        mutableStateOf("")
    }

    var isFalseTopicsOpen by remember {
        mutableStateOf(false)
    }


    val addExamViewModelState = addExamViewModel.state.value
    val lessonTopicTrackingViewModelTytTopicsState =
        lessonTopicTrackingViewModel.tytExamLessonsTopicState.value

    val biologyTopicList = remember { arrayListOf<String>() }
    val physicsTopicList = remember { arrayListOf<String>() }
    val chemicalTopicList = remember { arrayListOf<String>() }
    val mathTopicList = remember { arrayListOf<String>() }
    val turkishTopicList = remember { arrayListOf<String>() }
    val philosophyTopicList = remember { arrayListOf<String>() }
    val historyTopicList = remember { arrayListOf<String>() }
    val religionTopicList = remember { arrayListOf<String>() }
    val geographyTopicList = remember { arrayListOf<String>() }

    val biologyFalseTopic = rememberSaveable { arrayListOf<String>() }
    val physicsFalseTopic = rememberSaveable { arrayListOf<String>() }
    val chemicalFalseTopic = rememberSaveable { arrayListOf<String>() }
    val mathFalseTopic = rememberSaveable { arrayListOf<String>() }
    val turkishFalseTopic = rememberSaveable { arrayListOf<String>() }
    val philosophyFalseTopic = rememberSaveable { arrayListOf<String>() }
    val historyFalseTopic = rememberSaveable { arrayListOf<String>() }
    val religionFalseTopic = rememberSaveable { arrayListOf<String>() }
    val geographyFalseTopic = rememberSaveable { arrayListOf<String>() }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))



    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxHeight()) {
        Column() {
            LessonCorrectAndFalseInputs(
                "Türkçe",
                onCorrectValueChange = { turkishCorrect = it },
                onFalseValueChange = { turkishFalse = it },
                turkishCorrect,
                turkishFalse
            )

            LessonCorrectAndFalseInputs(
                "Sosyal",
                onCorrectValueChange = { socialCorrect = it },
                onFalseValueChange = { socialFalse = it },
                socialCorrect,
                socialFalse
            )

            LessonCorrectAndFalseInputs(
                "Matematik",
                onCorrectValueChange = { mathCorrect = it },
                onFalseValueChange = { mathFalse = it },
                mathCorrect,
                mathFalse
            )

            LessonCorrectAndFalseInputs(
                "Fen",
                onCorrectValueChange = { scienceCorrect = it },
                onFalseValueChange = { scienceFalse = it },
                scienceCorrect,
                scienceFalse
            )
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Yanlış Konuları Seç")
                Switch(checked = isFalseTopicsOpen, onCheckedChange = {
                    isFalseTopicsOpen = it
                })

            }
            if (isFalseTopicsOpen) {
                LaunchedEffect(Unit) {
                    lessonTopicTrackingViewModel.onEvent(LessonTopicTrackingEvent.GetTytLessonsTopics)
                }
                if (lessonTopicTrackingViewModelTytTopicsState.isLoading) {
                    LottieAnimation(
                        modifier = Modifier.fillMaxWidth(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                } else if (lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult != null) {
                    turkishTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Turkce)
                    philosophyTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Felsefe)
                    historyTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Tarih)
                    chemicalTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Kimya)
                    physicsTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Fizik)
                    religionTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.DinKulturu)
                    geographyTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Cografya)
                    biologyTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Biyoloji)
                    mathTopicList.addAll(lessonTopicTrackingViewModelTytTopicsState.tytTopicsResult.Matematik)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        LessonFalseTopics("Türkçe", turkishTopicList, turkishFalseTopic)
                        LessonFalseTopics("Coğragya", geographyTopicList, geographyFalseTopic)
                        LessonFalseTopics("Tarih", historyTopicList, philosophyFalseTopic)
                        LessonFalseTopics("Felsefe", philosophyTopicList, philosophyFalseTopic)
                        LessonFalseTopics("D.K.A.B.", religionTopicList, religionFalseTopic)
                        LessonFalseTopics("Matematik", mathTopicList, mathFalseTopic)
                        LessonFalseTopics("Fizik", physicsTopicList, physicsFalseTopic)
                        LessonFalseTopics("Kimya", chemicalTopicList, chemicalFalseTopic)
                        LessonFalseTopics("Biyoloji", biologyTopicList, biologyFalseTopic)
                    }
                }
            }
        }

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            onClick = {
                if(examName.isNullOrEmpty()){
                    isExamNameNullOrEmpty = true
                }else{
                    isExamNameNullOrEmpty = false
                    
                    val newTytExamModel = NewTytExamModel(
                        examName = examName,
                        aboutExam = aboutExam,
                        turkishCorrect = turkishCorrect.toIntOrNull() ?: 0,
                        turkishFalse = turkishFalse.toIntOrNull() ?: 0,
                        socialCorrect = socialCorrect.toIntOrNull() ?: 0,
                        socialFalse = socialFalse.toIntOrNull() ?: 0,
                        mathCorrect = mathCorrect.toIntOrNull() ?: 0,
                        mathFalse = mathFalse.toIntOrNull() ?: 0,
                        scienceCorrect = scienceCorrect.toIntOrNull() ?: 0,
                        scienceFalse = scienceFalse.toIntOrNull() ?: 0,
                        turkishFalseTopicList = turkishFalseTopic,
                        geographyFalseTopicList = geographyFalseTopic,
                        historyFalseTopicList = historyFalseTopic,
                        philosophyFalseTopicList = philosophyFalseTopic,
                        religionFalseTopicList = religionFalseTopic,
                        mathFalseTopicList = mathFalseTopic,
                        physicsFalseTopicList = physicsFalseTopic,
                        chemicalFalseTopicList = chemicalFalseTopic,
                        biologyFalseTopicList = biologyFalseTopic,
                        examDate = com.google.firebase.Timestamp.now()
                    )

                    addExamViewModel.onEvent(
                        AddExamEvent.AddTytExam(
                            newTytExamModel = newTytExamModel,
                            dataStoreViewModel.getUserUidFromDataStore()
                        ),
                    )
                }
                

            }
        ) {
            if (addExamViewModelState.isLoading) {
                CircularProgressIndicator()
            } else if (addExamViewModelState.error != null) {
                Text(text = "!! ${addExamViewModelState.error} !!")
            } else if(isExamNameNullOrEmpty){
                Text(text = "Sınav Adı Boş Bırakılamaz")
            }
            else {
                Text(text = "Kaydet")
            }

        }


    }
}

