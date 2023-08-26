package com.leventsurer.denemelerim.data.remote.dto

import com.leventsurer.denemelerim.domain.model.UserProfileInfoModel

data class UserModel(
    val aytExams:ArrayList<NewAytExamModel> = arrayListOf(),
    val email:String = "",
    val numberOfAytExam:Int = 0,
    val numberOfTytExam:Int= 0,
    val targetDepartment:String= "",
    val targetUniversity:String= "",
    val totalAytPoints:Double= 0.0,

    val totalTytPoints:Double= 0.0,
    val tytExams:ArrayList<NewTytExamModel> = arrayListOf(),
    val userName:String= "",
    val yksExamPoint:Double= 0.0

)



fun UserModel.toUserProfileInfoModel() : UserProfileInfoModel{
    return UserProfileInfoModel(userName,numberOfTytExam,numberOfAytExam,totalTytPoints/numberOfTytExam,totalAytPoints/numberOfAytExam,yksExamPoint,targetUniversity,targetDepartment)
}




