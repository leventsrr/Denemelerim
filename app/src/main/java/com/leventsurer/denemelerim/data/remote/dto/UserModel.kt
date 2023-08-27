package com.leventsurer.denemelerim.data.remote.dto

import com.leventsurer.denemelerim.domain.model.UserProfileExamModel
import com.leventsurer.denemelerim.domain.model.UserProfileInfoModel

data class UserModel(
    val aytExams: ArrayList<NewAytExamModel> = arrayListOf(),
    val email: String = "",
    val numberOfAytExam: Int = 0,
    val numberOfTytExam: Int = 0,
    val targetDepartment: String = "",
    val targetUniversity: String = "",
    val totalTytPoints: Double = 0.0,
    val totalEqualWeightPoints: Double = 0.0,
    val totalNumericalPoints: Double = 0.0,
    val totalVerbalPoints: Double = 0.0,
    val tytExams: ArrayList<NewTytExamModel> = arrayListOf(),
    val userName: String = "",
    val numericalYksExamPoint: Double = 0.0,
    val equalWeightYksExamPoint: Double = 0.0,
    val verbalYksExamPoint: Double = 0.0,
    val aytNumericalNetList:ArrayList<Double> = arrayListOf(),
    val aytEqualWeightNetList:ArrayList<Double> = arrayListOf(),
    val aytVerbalNetList:ArrayList<Double> = arrayListOf(),
    val tytNetList:ArrayList<Double> = arrayListOf()

)


fun UserModel.toUserProfileInfoModel(): UserProfileInfoModel {
    return UserProfileInfoModel(
        userName,
        numberOfTytExam,
        numberOfAytExam,
        totalTytPoints / numberOfTytExam,
        totalVerbalPoints / numberOfAytExam,
        totalEqualWeightPoints / numberOfAytExam,
        totalNumericalPoints / numberOfAytExam,
        numericalYksExamPoint,
        equalWeightYksExamPoint,
        verbalYksExamPoint,
        targetUniversity,
        targetDepartment
    )
}

fun UserModel.toUserProfileExamModel(): UserProfileExamModel {
    return UserProfileExamModel(
       tytNetList, aytNumericalNetList, aytEqualWeightNetList, aytVerbalNetList
    )
}






