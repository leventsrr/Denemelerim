package com.leventsurer.denemelerim.presentation.common.database

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.domain.model.UserProfileInfoModel


data class DatabaseState(
    val isLoading:Boolean = false,
    val error : String? = null,
    val result:Boolean? = null,
    val tytExams:List<NewTytExamModel>? = arrayListOf(),
    val aytExams:ArrayList<NewAytExamModel>? = arrayListOf(),
    val userProfileInfo:UserProfileInfoModel? = null
)