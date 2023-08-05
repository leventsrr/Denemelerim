package com.leventsurer.denemelerim.presentation.common.database

import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel


data class DatabaseState(
    val isLoading:Boolean = false,
    val error : String? = null,
    val result:Boolean? = null,
    val tytExams:List<NewTytExamModel>? = arrayListOf(),
    val aytExams:ArrayList<NewAytExamModel>? = arrayListOf()
)