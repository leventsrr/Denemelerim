package com.leventsurer.denemelerim.presentation.set_target_screen

import com.leventsurer.denemelerim.domain.model.UniversitiesAndDepartmentsModel

data class SetTargetState(
    val isLoading:Boolean = false,
    val error : String? = null,
    val result:Boolean? = null,
    val universitiesAndDepartments:List<UniversitiesAndDepartmentsModel>?=null,
)