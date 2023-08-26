package com.leventsurer.denemelerim.presentation.leaderboard_screen

import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.data.remote.dto.UserModel


data class LeaderboardState(
    val isLoading:Boolean = false,
    val users : List<UserModel>? = null,
    val error : String = "",
)