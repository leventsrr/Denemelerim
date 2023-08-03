package com.leventsurer.denemelerim.domain.repository
//DataStore source:https://www.youtube.com/watch?v=wkt59jo7Nh0
//https://developer.android.com/topic/libraries/architecture/datastore
interface DataStoreRepository {

    suspend fun putIsFirstLogin(key: String,value: Boolean)
    suspend fun getIsFirstLogin(key: String):Boolean?

    suspend fun putUserUid(key:String,value:String)
    suspend fun getUserUid(key: String):String?
    suspend fun clearUserUid(key: String)

}