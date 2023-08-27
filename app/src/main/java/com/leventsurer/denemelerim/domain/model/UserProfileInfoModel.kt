package com.leventsurer.denemelerim.domain.model

data class UserProfileInfoModel(
    val userName:String,
    val numberOfTytExam:Int,
    val numberOfAytExam:Int,
    val averageTytPoint:Double,
    val averageVerbalPoint:Double,
    val averageEqualWeightPoint:Double,
    val averageNumericalPoint:Double,
    val numericalYksPoint:Double,
    val equalWeightYksPoint:Double,
    val verbalYksPoint:Double,
    val targetUniversity:String,
    val targetDepartment:String
)
