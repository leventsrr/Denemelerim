package com.leventsurer.denemelerim.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthenticationApi(private val firebaseAuth: FirebaseAuth) {


    suspend fun signUp(userName:String,email:String,password:String):FirebaseUser{
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        result?.user?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(userName).build()
        )?.await()
        return result.user!!
    }

    suspend fun signIn(email: String,password: String):FirebaseUser{
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user!!
    }

    suspend fun logOut(){
        firebaseAuth.signOut()
    }

}