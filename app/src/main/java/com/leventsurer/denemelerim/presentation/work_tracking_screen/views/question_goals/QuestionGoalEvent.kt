package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals

import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel


sealed class QuestionGoalEvent {

    data class AddNewQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()

    data class GetQuestionGoals(val userUid: String) : QuestionGoalEvent()
    data class UpdateQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()

    data class DeleteQuestionGoal(val questionGoalModel: QuestionGoalModel, val userUid: String) :
        QuestionGoalEvent()

   object GetTytLessonsTopics: QuestionGoalEvent()

    object GetAytNumericalExamLessonsTopic: QuestionGoalEvent()
    object GetAytEqualWeightExamLessonsTopic: QuestionGoalEvent()

    data class ChangeExamLessonTopicStatus(val topicName:String,val isDone:Boolean,val userUid: String):
        QuestionGoalEvent()
    data class GetExamLessonTopicStatus(val userUid: String): QuestionGoalEvent()

}
