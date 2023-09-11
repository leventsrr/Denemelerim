package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel

data class LessonTopicTrackingState(

    val isLoading: Boolean = false,
    val error: String? = null,
    val tytTopicsResult: TytExamLessonsTopicsModel? = null,
    val aytNumericalExamLessonsTopic : AytNumericalExamLessonsTopicsModel? = null,
    val aytEqualWeightExamLessonsTopic : AytEqualWeightExamLessonsTopicsModel? = null,
    val changeExamLessonTopicStatusResult:Boolean?=null,
    val userExamLessonsTopicStatusResult:ArrayList<String>?=null
)