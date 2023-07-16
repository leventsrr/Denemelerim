package com.leventsurer.denemelerim.data.remote.dto

data class TytExam(
    val examName:String,
    val examPublisher:String,
    val turkishCorrectQuestion:Int,
    val turkishFalseQuestion:Int,
    val socialCorrectQueston:Int,
    val socialFalseQueston:Int,
    val mathCorrectQuestion:Int,
    val mathFalseQuestion:Int,
    val scienceCorrectQueston:Int,
    val scienceFalseQueston:Int,
)
