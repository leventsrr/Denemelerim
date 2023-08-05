package com.leventsurer.denemelerim.presentation.set_target_screen

data class SetTargetState(
    val isLoading:Boolean = false,
    val error : String? = null ,
    val result:Boolean? = null ,
)