package com.leventsurer.denemelerim.data.repository

import android.util.Log
import com.leventsurer.denemelerim.data.remote.DatabaseApi
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseApi: DatabaseApi
) : DatabaseRepository {

    override suspend fun setTarget(
        university: String,
        department: String,
        userUid: String
    ) {
        databaseApi.setTarget(university, department, userUid)
    }

    override suspend fun addNewUser(userUid: String, user: UserModel,userName: String){
        databaseApi.addNewUser(userUid, user,userName)
    }

    override suspend fun getUserTytExam(userUid: String): ArrayList<NewTytExamModel>? {
            return databaseApi.getUserTytExams(userUid)
    }

    override suspend fun getUserAytExam(userUid: String): ArrayList<NewAytExamModel>? {
        return databaseApi.getUserAytExams(userUid)
    }
    override suspend fun addTytExam(newTytExamModel: NewTytExamModel, userUid: String) {
        databaseApi.addNewTytExam(newTytExamModel,userUid)
    }

    override suspend fun addAytExam(newAytExamModel: NewAytExamModel, userUid: String) {
        databaseApi.addNewAytExam(newAytExamModel,userUid)
    }

    override suspend fun getUserProfileInfo(userUid: String): UserModel? {
        return databaseApi.getUserProfileInfo(userUid)
    }

    override suspend fun getUsersToLeaderboard(
        universityName: String,
        departmentName: String,
        pointType:String
    ): List<UserModel> {
        return databaseApi.getUsersToLeaderboard(universityName, departmentName,pointType)
    }

    override suspend fun addNewQuestionGoal(
        questionGoalModel:QuestionGoalModel,
        userUid: String
    ) {
        databaseApi.addNewQuestionGoal(questionGoalModel,userUid)
    }

    override suspend fun getQuestionGoals(userUid: String): ArrayList<QuestionGoalModel>? {
        return databaseApi.getQuestionGoals(userUid)
    }

    override suspend fun updateQuestionGoal(userUid: String, questionGoalModel: QuestionGoalModel) {
         databaseApi.updateQuestionGoal(userUid, questionGoalModel)
    }

    override suspend fun deleteQuestionGoal(userUid: String, questionGoalModel: QuestionGoalModel) {
        return databaseApi.deleteQuestionGoal(userUid, questionGoalModel)
    }

    override suspend fun deleteUserAccount(userUid: String) {
        databaseApi.deleteUserAccount(userUid)
    }

    override suspend fun changeExamLessonTopicStatus(topicName:String,isDone:Boolean,userUid: String) {
        Log.e("kontrol","status repo:${isDone}")
        databaseApi.changeExamLessonTopicStatus(topicName, isDone, userUid)
    }

    override suspend fun getUserExamLessonsTopicStatus(userUid: String): ArrayList<String> {
        return databaseApi.getUserExamLessonsTopicStatus(userUid)
    }

}