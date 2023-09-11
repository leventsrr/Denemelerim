package com.leventsurer.denemelerim.presentation.tracking_screen.question_goals

import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel


sealed class QuestionGoalEvent {

    data class AddNewQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()

    data class GetQuestionGoals(val userUid: String) : QuestionGoalEvent()
    data class UpdateQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()

    data class DeleteQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()





}
