package com.leventsurer.denemelerim.presentation.common.database

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.database_use_case.AddNewUserUseCase
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetAytExamsUseCase
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetTytExamsUseCase
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetUserProfileInfoUseCase
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val addNewUserUseCase: AddNewUserUseCase,
    private val getTytExamsUseCase: GetTytExamsUseCase,
    private val getAytExamsUseCase: GetAytExamsUseCase,
    private val getUserProfileInfoUseCase: GetUserProfileInfoUseCase,
) : ViewModel(){

    private val _addNewUserState = mutableStateOf(DatabaseState())
    val addNewUserState: State<DatabaseState> = _addNewUserState

    private val _getTytExamsState = mutableStateOf(DatabaseState())
    val getTytExamsState: MutableState<DatabaseState> = _getTytExamsState

    private val _getAytExamsState = mutableStateOf(DatabaseState())
    val getAytExamsState: State<DatabaseState> = _getAytExamsState



    private var job: Job? = null


    private fun addNewUser(userUid:String){
        job?.cancel()
        job = addNewUserUseCase.executeAddNewUser(userUid).onEach {
            when(it){
                is Resource.Loading->{
                    _addNewUserState.value = DatabaseState(isLoading = true)
                }
                is Resource.Success->{

                    _addNewUserState.value = DatabaseState(result = it.data, isLoading = false)
                }

                is Resource.Error-> {
                    _addNewUserState.value = DatabaseState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getTytExams(userUid: String){
        getTytExamsUseCase.executeGetTytExams(userUid).onEach {
            when(it){
                is Resource.Loading->{
                    _getTytExamsState.value = DatabaseState(isLoading = true)
                }
                is Resource.Success->{
                    _getTytExamsState.value = DatabaseState(tytExams = it.data, isLoading = false)
                }

                is Resource.Error-> {
                    _getTytExamsState.value = DatabaseState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAytExams(userUid: String){
        job?.cancel()
        job = getAytExamsUseCase.executeGetAytExams(userUid).onEach {
            when(it){
                is Resource.Loading->{
                    _getAytExamsState.value = DatabaseState(isLoading = true)
                }
                is Resource.Success->{
                    Log.e("kontrol",it.data.toString())
                    _getAytExamsState.value = DatabaseState(aytExams = it.data, isLoading = false)
                }

                is Resource.Error-> {
                    _getAytExamsState.value = DatabaseState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun onEvent(event: DatabaseEvent) {
        when (event) {
            is DatabaseEvent.AddNewUser -> {
                addNewUser(event.userUid)
            }

            is DatabaseEvent.GetTytExams->{
                getTytExams(event.userUid)
            }

            is DatabaseEvent.GetAytExams->{
                getAytExams(event.userUid)
            }

        }
    }


}