package com.leventsurer.denemelerim.domain.model

data class UserModel(
    val aytExams:ArrayList<NewAytExamModel>,
    val email:String,
    val numberOfAytExam:Number,
    val numberOfTytExam:Number,
    val targetDepartment:String,
    val targetUniversity:String,
    val totalAytPoints:Number,
    val totalTytPoints:Number,
    val tytExams:ArrayList<NewTytExamModel>,
    val userName:String,
    val yksExamPoint:Number

)
