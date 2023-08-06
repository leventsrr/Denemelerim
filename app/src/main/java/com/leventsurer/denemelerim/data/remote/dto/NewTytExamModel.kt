package com.leventsurer.denemelerim.data.remote.dto

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import java.time.LocalDate
import java.util.Date


data class NewTytExamModel(
    val examName:String = "",
    val aboutExam:String?= "",
    val turkishCorrect:Int = 0,
    val turkishFalse:Int= 0,
    var turkishNet:Double = 0.0,
    val socialCorrect:Int= 0,
    val socialFalse:Int= 0,
    var socialNet:Double=0.0,
    val mathCorrect:Int= 0,
    val mathFalse:Int= 0,
    var mathNet:Double=0.0,
    val scienceCorrect:Int= 0,
    val scienceFalse:Int= 0,
    var scienceNet:Double=0.0,
    var totalNet:Double = 0.0,
    var totalPoint:Double = 0.0,
    @ServerTimestamp
    val examDate: Timestamp = Timestamp.now()
)
