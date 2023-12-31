package com.leventsurer.denemelerim.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthenticationApi(private val firebaseAuth: FirebaseAuth) {


    suspend fun signUp(userName:String,email:String,password:String):FirebaseUser{
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user!!
    }

    suspend fun signIn(email: String,password: String):FirebaseUser{
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user!!
    }
    
     fun logOut(){
        firebaseAuth.signOut()
    }

     fun deleteAccount(){
        firebaseAuth.currentUser!!.delete()
    }



}