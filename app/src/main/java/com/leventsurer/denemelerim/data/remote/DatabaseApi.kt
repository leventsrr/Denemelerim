package com.leventsurer.denemelerim.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.model.UserModel
import com.leventsurer.denemelerim.util.Constants.TARGET_DEPARTMENT
import com.leventsurer.denemelerim.util.Constants.TARGET_UNIVERSITY
import com.leventsurer.denemelerim.util.Constants.USER_COLLECTION
import kotlinx.coroutines.tasks.await

class DatabaseApi( private val database: FirebaseFirestore) {



    suspend fun addNewUser(userUid: String,user:UserModel){



        try {
            database.collection(USER_COLLECTION).document(userUid).set(user).await()
        }catch (e:Exception){
            Log.e("kontrol",e.message.toString())
        }
    }

    suspend fun setTarget(university:String,department:String,userUid:String){
        try {

            database.collection(USER_COLLECTION).document(userUid).update(TARGET_UNIVERSITY,university).await()
            database.collection(USER_COLLECTION).document(userUid).update(TARGET_DEPARTMENT,department).await()
        }catch (e:Exception){
            Log.e("kontrol",e.message.toString())
        }

    }



}