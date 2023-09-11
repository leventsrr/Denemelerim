package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel


interface DatabaseRepository {

    suspend fun setTarget(university: String, department: String, userUid: String)
    suspend fun addNewUser(userUid: String, user: UserModel, userName: String)

    suspend fun getUserTytExam(userUid: String): ArrayList<NewTytExamModel>?
    suspend fun getUserAytExam(userUid: String): ArrayList<NewAytExamModel>?
    suspend fun addTytExam(newTytExamModel: NewTytExamModel, userUid: String)
    suspend fun addAytExam(newAytExamModel: NewAytExamModel, userUid: String)

    suspend fun getUserProfileInfo(userUid: String): UserModel?

    suspend fun getUsersToLeaderboard(
        universityName: String,
        departmentName: String,
        pointType: String
    ): List<UserModel>

    suspend fun addNewQuestionGoal(
        questionGoalModel: QuestionGoalModel,
        userUid: String
    )

    suspend fun getQuestionGoals(userUid: String):ArrayList<QuestionGoalModel>?

    suspend fun updateQuestionGoal(userUid: String,questionGoalModel: QuestionGoalModel)
    suspend fun deleteQuestionGoal(userUid: String,questionGoalModel: QuestionGoalModel)
    suspend fun deleteUserAccount(userUid: String)

    suspend fun changeExamLessonTopicStatus(topicName:String,isDone:Boolean,userUid: String)

    suspend fun getUserExamLessonsTopicStatus(userUid: String):ArrayList<String>

}