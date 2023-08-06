package com.leventsurer.denemelerim.data.repository

import android.util.Log
import com.leventsurer.denemelerim.data.remote.DatabaseApi
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseApi: DatabaseApi
) : DatabaseRepository {

    override suspend fun setTarget(
        university: String,
        department: String,
        userUid: String
    ) {
        databaseApi.setTarget(university, department, userUid)
    }

    override suspend fun addNewUser(userUid: String, user: UserModel){

        Log.e("kontrol", "repoImpl")
        databaseApi.addNewUser(userUid, user)

    }

    override suspend fun getUserTytExam(userUid: String): ArrayList<NewTytExamModel>? {
            return databaseApi.getUserTytExams(userUid)
    }

    override suspend fun getUserAytExam(userUid: String): ArrayList<NewAytExamModel>? {
        return databaseApi.getUserAytExams(userUid)
    }
    override suspend fun addTytExam(newTytExamModel: NewTytExamModel, userUid: String) {
        databaseApi.addNewTytExam(newTytExamModel,userUid)
    }

    override suspend fun addAytExam(newAytExamModel: NewAytExamModel, userUid: String) {
        databaseApi.addNewAytExam(newAytExamModel,userUid)
    }

    override suspend fun getUserProfileInfo(userUid: String): UserModel? {
        return databaseApi.getUserProfileInfo(userUid)
    }

}