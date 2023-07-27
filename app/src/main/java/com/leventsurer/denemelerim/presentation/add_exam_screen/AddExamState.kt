package com.leventsurer.denemelerim.presentation.add_exam_screen

import com.google.firebase.auth.FirebaseUser


data class AddExamState(
    val isLoading:Boolean = false,
    val user : FirebaseUser? = null,
    val error : String = "",
)