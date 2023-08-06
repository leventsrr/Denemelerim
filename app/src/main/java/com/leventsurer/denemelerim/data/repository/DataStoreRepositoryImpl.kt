package com.leventsurer.denemelerim.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.util.Constants.DATASTORE_NAME
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.Exception

import javax.inject.Inject



private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)


class DataStoreRepositoryImpl @Inject constructor(
    private val context :Context
) :DataStoreRepository {

    override suspend fun putUserUid(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getUserUid(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun clearUserUid(key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            if(it.contains(preferenceKey)){
                it.remove(preferenceKey)
            }
        }
    }

    override suspend fun putIsFirstLoginInfo(key: String, value: Boolean) {
        val preferenceKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }
    override suspend fun getIsFirstLoginInfo(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        }catch (e:Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun clearAllDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }

}