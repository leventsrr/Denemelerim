package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.domain.model.UserModel
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun setTarget(university:String,department:String,userUid:String)
    suspend fun addNewUser(userUid:String,user: UserModel)

}