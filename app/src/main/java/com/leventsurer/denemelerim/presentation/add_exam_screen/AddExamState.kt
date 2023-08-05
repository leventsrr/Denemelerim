package com.leventsurer.denemelerim.presentation.add_exam_screen


data class AddExamState(
    val isLoading:Boolean = false,
    val result : Boolean? = null,
    val error : String? = null,
)