package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel


data class QuestionGoalState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val result: List<QuestionGoalModel>? = null,
    val tytTopicsResult: TytExamLessonsTopicsModel? = null,
    val aytNumericalExamLessonsTopic :AytNumericalExamLessonsTopicsModel? = null,
    val aytEqualWeightExamLessonsTopic :AytEqualWeightExamLessonsTopicsModel? = null,
    val changeExamLessonTopicStatusResult:Boolean?=null,
    val userExamLessonsTopicStatus:ArrayList<String>?=null
)