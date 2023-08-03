package com.leventsurer.denemelerim.domain.model

import com.google.firebase.firestore.FieldValue


data class NewTytExamModel(
    val examName:String,
    val aboutExam:String?,
    val turkishCorrect:Int,
    val turkishFalse:Int,
    val socialCorrect:Int,
    val socialFalse:Int,
    val mathCorrect:Int,
    val mathFalse:Int,
    val scienceCorrect:Int,
    val scienceFalse:Int,
    var totalNet:Double = 0.0,
    var totalPoint:Double = 0.0,
    val examDate: FieldValue
)
