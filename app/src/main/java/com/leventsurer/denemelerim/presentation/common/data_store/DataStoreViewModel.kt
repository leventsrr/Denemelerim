package com.leventsurer.denemelerim.presentation.common.data_store

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.presentation.register_screen.RegisterState
import com.leventsurer.denemelerim.util.Constants.IS_FIRST_LOGIN
import com.leventsurer.denemelerim.util.Constants.USER_UID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) :ViewModel(){

    /*private val _userUid = mutableStateOf("")
    val userUid = _userUid



    private val _state = mutableStateOf(RegisterState())
    val state : State<RegisterState> = _state*/

     fun putUserUidToDataStore(userUid:String) = runBlocking {
         dataStoreRepository.putUserUid(USER_UID,userUid)
    }

     fun getUserUidFromDataStore():String= runBlocking{
         dataStoreRepository.getUserUid(USER_UID)!!
    }

     fun putIsFirstLoginInfo()= runBlocking{
        dataStoreRepository.putIsFirstLoginInfo(IS_FIRST_LOGIN,true)
     }

     fun getIsFirstLoginInfo():Boolean?= runBlocking{
        dataStoreRepository.getIsFirstLoginInfo(IS_FIRST_LOGIN)
     }

    fun clearAllDataStore()= runBlocking{
        dataStoreRepository.clearAllDataStore()
    }





}