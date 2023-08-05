package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.model.UserModel


interface DatabaseRepository {

    suspend fun setTarget(university:String,department:String,userUid:String)
    suspend fun addNewUser(userUid:String,user: UserModel)

    suspend fun getUserTytExam(userUid: String):ArrayList<NewTytExamModel>?
    suspend fun getUserAytExam(userUid: String):ArrayList<NewAytExamModel>?
    suspend fun addTytExam(newTytExamModel: NewTytExamModel,userUid: String)
    suspend fun addAytExam(newAytExamModel: NewAytExamModel,userUid: String)

}