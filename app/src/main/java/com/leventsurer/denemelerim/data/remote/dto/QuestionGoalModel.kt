package com.leventsurer.denemelerim.data.remote.dto

import java.util.UUID

data class QuestionGoalModel(
    val goalName: String = "",
    val goalQuestionQuantity: Int = 0,
    var solvedQuestionQuantity: Int = 0,
    var rightQuestionQuantity: Int= 0,
    var falseQuestionQuantity: Int= 0,
    var emptyQuestionQuantity: Int= 0,
)
