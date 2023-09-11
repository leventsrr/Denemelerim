package com.leventsurer.denemelerim.presentation.tracking_screen.question_goals

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel


data class QuestionGoalState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val result: List<QuestionGoalModel>? = null,
)