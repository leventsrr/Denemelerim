package com.leventsurer.denemelerim.presentation.register_screen

import com.google.firebase.auth.FirebaseUser


data class RegisterState(
    val isLoading:Boolean = false,
    val user : FirebaseUser? = null,
    val error : String? = null,
)