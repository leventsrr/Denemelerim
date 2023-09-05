package com.leventsurer.denemelerim.data.remote.dto

data class QuestionGoalModel(
    val goalName: String = "",
    val goalQuestionQuantity: Int = 0,
    val solvedQuestionQuantity: Int = 0,
    val rightQuestionQuantity: Int= 0,
    val falseQuestionQuantity: Int= 0,
    val emptyQuestionQuantity: Int= 0
)
