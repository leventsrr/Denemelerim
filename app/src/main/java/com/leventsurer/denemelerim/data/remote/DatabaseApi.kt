package com.leventsurer.denemelerim.data.remote

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.util.Constants.TARGET_DEPARTMENT
import com.leventsurer.denemelerim.util.Constants.TARGET_UNIVERSITY
import com.leventsurer.denemelerim.util.Constants.USER_COLLECTION
import kotlinx.coroutines.tasks.await
import java.io.IOException

class DatabaseApi(private val database: FirebaseFirestore) {

    suspend fun addNewTytExam(newTytExamModel: NewTytExamModel, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("tytExams", FieldValue.arrayUnion(newTytExamModel)).await()
        } catch (e: java.lang.Exception) {
            Log.e("kontrol", e.message.toString())
        }
    }


    suspend fun addNewAytExam(newAytExamModel: NewAytExamModel, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("aytExams", FieldValue.arrayUnion(newAytExamModel)).await()
        } catch (e: java.lang.Exception) {
            Log.e("kontrol", e.message.toString())
        }
    }

    suspend fun addNewUser(userUid: String, user: UserModel) {
        try {
            database.collection(USER_COLLECTION).document(userUid).set(user).await()
        } catch (e: Exception) {
            Log.e("kontrol", e.message.toString())
        }
    }

    suspend fun setTarget(university: String, department: String, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update(TARGET_UNIVERSITY, university).await()
            database.collection(USER_COLLECTION).document(userUid)
                .update(TARGET_DEPARTMENT, department).await()
        } catch (e: Exception) {
            Log.e("kontrol", e.message.toString())
        }

    }

    suspend fun getUserTytExams(userUid: String): ArrayList<NewTytExamModel>? {
        val userDocument = database.collection(USER_COLLECTION).document(userUid).get().await()
        return userDocument.toObject(UserModel::class.java)?.tytExams

    }

    suspend fun getUserAytExams(userUid: String): ArrayList<NewAytExamModel>? {
        val userDocument = database.collection(USER_COLLECTION).document(userUid).get().await()
        return userDocument.toObject(UserModel::class.java)?.aytExams

    }

    suspend fun getUserProfileInfo(userUid: String): UserModel? {
        val userDocumentSnapshot =
            database.collection(USER_COLLECTION).document(userUid).get().await()
        return userDocumentSnapshot.toObject(UserModel::class.java)
    }


}