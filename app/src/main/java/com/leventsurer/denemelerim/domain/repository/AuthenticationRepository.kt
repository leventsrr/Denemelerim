package com.leventsurer.denemelerim.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.util.Resource

interface AuthenticationRepository {

    suspend fun signIn(email:String,password: String):FirebaseUser
    suspend fun signUp(userName:String,email:String,password:String):FirebaseUser
    suspend fun logout()
}