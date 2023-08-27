package com.leventsurer.denemelerim.presentation.statistics_screen

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel


data class StatisticsState(
    val isLoading:Boolean = false,
    val tytExams:ArrayList<NewTytExamModel>? = null,
    val aytExams:ArrayList<NewAytExamModel>? = null,
    val error : String = "",
)