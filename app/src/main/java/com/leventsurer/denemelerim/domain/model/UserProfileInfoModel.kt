package com.leventsurer.denemelerim.domain.model

data class UserProfileInfoModel(
    val userName:String,
    val numberOfTytExam:Int,
    val numberOfAytExam:Int,
    val averageTytPoint:Double,
    val averageAytPoint:Double,
    val averageYksPoint:Double,
    val targetUniversity:String,
    val targetDepartment:String
)
