package com.leventsurer.denemelerim.presentation.login_screen

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val isLoading:Boolean = false,
    val user : FirebaseUser? = null,
    val error : String = "",
)