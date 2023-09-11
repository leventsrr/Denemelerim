package com.leventsurer.denemelerim.data.remote

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.util.Constants.TARGET_DEPARTMENT
import com.leventsurer.denemelerim.util.Constants.TARGET_UNIVERSITY
import com.leventsurer.denemelerim.util.Constants.USER_COLLECTION
import com.leventsurer.denemelerim.util.enums.PointType
import kotlinx.coroutines.tasks.await

class DatabaseApi(private val database: FirebaseFirestore) {
    //Add new tyt exam and update user's totalTytQuantity and total Tyt point
    suspend fun addNewTytExam(newTytExamModel: NewTytExamModel, userUid: String) {
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("tytExams", FieldValue.arrayUnion(newTytExamModel)).await()
            database.collection(USER_COLLECTION).document(userUid)
                .update("tytNetList", FieldValue.arrayUnion(newTytExamModel.totalNet)).await()

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

            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "aytNumericalNetList",
                    FieldValue.arrayUnion(newAytExamModel.numericalTotalNet)
                ).await()
            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "aytEqualWeightNetList",
                    FieldValue.arrayUnion(newAytExamModel.equalWeightTotalNet)
                ).await()
            database.collection(USER_COLLECTION).document(userUid)
                .update("aytVerbalNetList", FieldValue.arrayUnion(newAytExamModel.verbalTotalNet))
                .await()

            val user = database.collection(USER_COLLECTION).document(userUid).get().await()
                .toObject(UserModel::class.java)

            val aytQuantity = user?.numberOfAytExam
            database.collection(USER_COLLECTION).document(userUid)
                .update("numberOfAytExam", aytQuantity!!.toInt() + 1).await()

            val totalEqualWeightPoints = user.totalEqualWeightPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "totalEqualWeightPoints",
                    totalEqualWeightPoints + newAytExamModel.equalWeightPoint
                ).await()

            val totalNumericalPoints = user.totalNumericalPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "totalNumericalPoints",
                    totalNumericalPoints + newAytExamModel.numericalPoint
                ).await()

            val totalVerbalPoints = user.totalVerbalPoints
            database.collection(USER_COLLECTION).document(userUid)
                .update("totalVerbalPoints", totalVerbalPoints + newAytExamModel.verbalPoint)
                .await()


            val newTotalNumericalPoint =
                database.collection(USER_COLLECTION).document(userUid).get().await()
                    .toObject(UserModel::class.java)?.totalNumericalPoints
            val newTotalEqualWeightPoint =
                database.collection(USER_COLLECTION).document(userUid).get().await()
                    .toObject(UserModel::class.java)?.totalEqualWeightPoints
            val newTotalVerbalPoint =
                database.collection(USER_COLLECTION).document(userUid).get().await()
                    .toObject(UserModel::class.java)?.totalVerbalPoints
            val newAytQuantity =
                database.collection(USER_COLLECTION).document(userUid).get().await()
                    .toObject(UserModel::class.java)?.numberOfAytExam

            val numericalYksExamPoint =
                (newTotalNumericalPoint!!.toDouble() / newAytQuantity!!.toDouble()) * (6.0 / 10.0)
            +(user.totalTytPoints / user.numberOfTytExam.toDouble()) * (4.0 / 10.0)

            database.collection(USER_COLLECTION).document(userUid)
                .update("numericalYksExamPoint", numericalYksExamPoint).await()

            val equalWeightYksExamPoint =
                (newTotalEqualWeightPoint!!.toDouble() / newAytQuantity.toDouble()) * (6.0 / 10.0)
            +(user.totalTytPoints / user.numberOfTytExam.toDouble()) * (4.0 / 10.0)
            Log.e("kontrol", "equal:${equalWeightYksExamPoint}")
            database.collection(USER_COLLECTION).document(userUid)
                .update("equalWeightYksExamPoint", equalWeightYksExamPoint).await()

            val verbalYksExamPoint =
                (newTotalVerbalPoint!!.toDouble() / newAytQuantity.toDouble()) * (6.0 / 10.0)
            +(user.totalTytPoints / user.numberOfTytExam.toDouble()) * (4.0 / 10.0)
            Log.e("kontrol", "verbalYksExamPoint:${verbalYksExamPoint}")
            database.collection(USER_COLLECTION).document(userUid)
                .update("verbalYksExamPoint", verbalYksExamPoint).await()

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
        departmentName: String,
        pointType: String
    ): List<UserModel> {
        val filterQuery: String
        when (pointType) {
            PointType.EQUAL_WEIGHT.pointType -> {
                filterQuery = "equalWeightYksExamPoint"
            }

            PointType.NUMERICAL.pointType -> {
                filterQuery = "numericalYksExamPoint"
            }

            PointType.VERBAL.pointType -> {
                filterQuery = "verbalYksExamPoint"
            }

            else -> {
                filterQuery = ""
            }
        }
        val usersQuerySnapShot =
            database.collection(USER_COLLECTION).whereEqualTo("targetUniversity", universityName)
                .whereEqualTo("targetDepartment", departmentName).orderBy(
                    filterQuery,
                    Query.Direction.DESCENDING
                ).get().await()
        Log.e("kontrol", "DatabaseApi size:${usersQuerySnapShot.documents.size}")
        return usersQuerySnapShot.map { it.toObject(UserModel::class.java) }
    }


    suspend fun addNewQuestionGoal(
        questionGoalModel: QuestionGoalModel,
        userUid: String
    ) {
        database.collection(USER_COLLECTION).document(userUid)
            .update("questionGoals", FieldValue.arrayUnion(questionGoalModel)).await()
    }

    suspend fun getQuestionGoals(
        userUid: String
    ): ArrayList<QuestionGoalModel>? {
        Log.e(
            "kontrol", "userModel:${
                database.collection(USER_COLLECTION).document(userUid).get().await()
                    .toObject(UserModel::class.java)?.questionGoals
            }"
        )
        return database.collection(USER_COLLECTION).document(userUid).get().await()
            .toObject(UserModel::class.java)?.questionGoals
    }

    suspend fun updateQuestionGoal(userUid: String, questionGoalModel: QuestionGoalModel) {
        val questionGoals = database.collection(USER_COLLECTION).document(userUid).get().await()
            .toObject(UserModel::class.java)?.questionGoals
        for (questionGoal in questionGoals!!) {
            if (questionGoal.goalName == questionGoalModel.goalName && questionGoal.goalQuestionQuantity == questionGoalModel.goalQuestionQuantity) {
                questionGoal.solvedQuestionQuantity = questionGoalModel.solvedQuestionQuantity
                questionGoal.rightQuestionQuantity = questionGoalModel.rightQuestionQuantity
                questionGoal.falseQuestionQuantity = questionGoalModel.falseQuestionQuantity
                questionGoal.emptyQuestionQuantity = questionGoalModel.emptyQuestionQuantity
            }
        }

        database.collection(USER_COLLECTION).document(userUid)
            .update("questionGoals", questionGoals).await()


    }


    suspend fun deleteQuestionGoal(userUid: String, questionGoalModel: QuestionGoalModel) {
        val questionGoals = database.collection(USER_COLLECTION).document(userUid).get().await()
            .toObject(UserModel::class.java)?.questionGoals

        val questionGoalsIterator = questionGoals!!.iterator()
        while (questionGoalsIterator.hasNext()) {
            val questionGoal = questionGoalsIterator.next()
            if (questionGoalModel.goalName == questionGoal.goalName && questionGoalModel.goalQuestionQuantity == questionGoal.goalQuestionQuantity) {
                questionGoalsIterator.remove()
            }
        }
        try {
            database.collection(USER_COLLECTION).document(userUid)
                .update("questionGoals", questionGoals).await()

        } catch (e: Exception) {
            Log.e("kontrol", "error:${e.message}")
        }

    }


    suspend fun deleteUserAccount(userUid: String) {
        database.collection(USER_COLLECTION).document(userUid).delete().await()
    }


    suspend fun getUserExamLessonsTopicStatus(userUid: String): ArrayList<String> {
        return database.collection(USER_COLLECTION).document(userUid).get().await()
            .toObject(UserModel::class.java)?.lessonsTopic!!
    }

    suspend fun changeExamLessonTopicStatus(topicName: String, isDone: Boolean, userUid: String) {
        if (isDone) {
            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "lessonsTopic",
                    FieldValue.arrayUnion(topicName)
                ).await()
        } else {
            database.collection(USER_COLLECTION).document(userUid)
                .update(
                    "lessonsTopic",
                    FieldValue.arrayRemove(topicName)
                ).await()
        }
    }
}

