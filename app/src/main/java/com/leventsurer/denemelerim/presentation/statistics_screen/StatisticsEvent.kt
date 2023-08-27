package com.leventsurer.denemelerim.presentation.statistics_screen



sealed class StatisticsEvent{

    data class GetTytExams(val userUid:String):StatisticsEvent()
    data class GetAytExams(val userUid:String):StatisticsEvent()

}
