package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow

//DataStore source:https://www.youtube.com/watch?v=wkt59jo7Nh0
//https://developer.android.com/topic/libraries/architecture/datastore
interface DataStoreRepository {

    suspend fun putUserUid(key:String,value:String)
    suspend fun getUserUid(key: String):String?
    suspend fun clearUserUid(key: String)

}