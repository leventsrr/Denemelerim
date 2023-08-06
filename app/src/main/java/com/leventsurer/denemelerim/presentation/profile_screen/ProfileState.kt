package com.leventsurer.denemelerim.presentation.profile_screen

import com.leventsurer.denemelerim.domain.model.UserProfileInfoModel


data class ProfileState(
    val isLoading:Boolean = false,
    val error : String? = null ,
    val result:UserProfileInfoModel? = null ,
)