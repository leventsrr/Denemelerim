package com.leventsurer.denemelerim.presentation.common.database



data class DatabaseState(
    val isLoading:Boolean = false,
    val error : String? = null ,
    val result:Boolean? = null ,
)