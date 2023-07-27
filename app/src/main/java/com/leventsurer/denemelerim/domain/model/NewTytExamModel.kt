package com.leventsurer.denemelerim.domain.model

data class NewTytExamModel(
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
)
