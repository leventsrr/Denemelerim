package com.leventsurer.denemelerim.presentation.set_target_screen

import com.google.firebase.auth.FirebaseUser


data class SetTargetState(
    val isLoading:Boolean = false,
    val error : String = "",
)