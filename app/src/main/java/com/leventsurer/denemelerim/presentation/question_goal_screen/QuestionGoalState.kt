package com.leventsurer.denemelerim.presentation.question_goal_screen

import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel



data class QuestionGoalState(
    val isLoading:Boolean = false,
    val error : String? = null,
    val result: List<QuestionGoalModel>? = null,

    )