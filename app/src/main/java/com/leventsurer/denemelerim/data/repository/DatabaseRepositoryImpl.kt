package com.leventsurer.denemelerim.data.repository

import android.util.Log
import com.leventsurer.denemelerim.data.remote.DatabaseApi
import com.leventsurer.denemelerim.domain.model.UserModel
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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



}