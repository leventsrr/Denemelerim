package com.leventsurer.denemelerim.data.remote

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.util.Constants.TARGET_DEPARTMENT
import com.leventsurer.denemelerim.util.Constants.TARGET_UNIVERSITY
import com.leventsurer.denemelerim.util.Constants.USER_COLLECTION
import kotlinx.coroutines.tasks.await

class DatabaseApi(private val database: FirebaseFirestore) {
    //Add new tyt exam and update user's totalTytQuantity and total Tyt point
    suspend fun addNewTytExam(newTytExamModel: NewTytExamModel, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("tytExams", FieldValue.arrayUnion(newTytExamModel)).await()
            val user = database.collection(USER_COLLECTION).document(userUid).get().await()
            val tytQuantity = user.toObject(UserModel::class.java)?.numberOfTytExam
            val totalTytPoints = user.toObject(UserModel::class.java)?.totalTytPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update("numberOfTytExam", tytQuantity!!.toInt() + 1).await()
            database.collection(USER_COLLECTION).document(userUid)
                .update("totalTytPoints", totalTytPoints!!.toDouble() + newTytExamModel.totalPoint)
                .await()
        } catch (e: java.lang.Exception) {
            Log.e("kontrol", e.message.toString())
        }
    }

    //add new ayt exam and update user's ayt exams information
    suspend fun addNewAytExam(newAytExamModel: NewAytExamModel, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("aytExams", FieldValue.arrayUnion(newAytExamModel)).await()
            val user = database.collection(USER_COLLECTION).document(userUid).get().await()

            val aytQuantity = user.toObject(UserModel::class.java)?.numberOfAytExam
            database.collection(USER_COLLECTION).document(userUid).update("numberOfAytExam", aytQuantity!!.toInt() + 1).await()

            val totalEqualWeightPoints = user.toObject(UserModel::class.java)?.totalEqualWeightPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update("totalEqualWeightPoints", totalEqualWeightPoints!!.toDouble() + newAytExamModel.equalWeightPoint).await()

            val totalNumericalPoints = user.toObject(UserModel::class.java)?.totalNumericalPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update("totalNumericalPoints", totalNumericalPoints!!.toDouble() + newAytExamModel.numericalPoint).await()

            val totalVerbalPoints = user.toObject(UserModel::class.java)?.totalVerbalPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update("totalVerbalPoints", totalVerbalPoints!!.toDouble() + newAytExamModel.verbalPoint).await()

        } catch (e: java.lang.Exception) {
            Log.e("kontrol", e.message.toString())
        }
    }

    suspend fun addNewUser(userUid: String, user: UserModel, userName: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid).set(user).await()
            database.collection(USER_COLLECTION).document(userUid).update("userName", userName)
                .await()
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

    suspend fun getUsersToLeaderboard(
        universityName: String,
        departmentName: String
    ): List<UserModel> {
        val usersQuerySnapShot =
            database.collection(USER_COLLECTION).whereEqualTo("targetUniversity", universityName)
                .whereEqualTo("targetDepartment", departmentName).orderBy(
                    "yksExamPoint",
                    Query.Direction.DESCENDING
                ).get().await()

        return usersQuerySnapShot.map { it.toObject(UserModel::class.java) }
    }


}