package com.leventsurer.denemelerim.presentation.common.database

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.domain.use_case.add_new_user.AddNewUserUseCase
import com.leventsurer.denemelerim.presentation.register_screen.RegisterState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetState
import com.leventsurer.denemelerim.util.Constants
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject



@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val addNewUserUseCase: AddNewUserUseCase
) : ViewModel(){

    private val _addNewUserState = mutableStateOf(DatabaseState())
    val addNewUserState: State<DatabaseState> = _addNewUserState


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


    fun onEvent(event: DatabaseEvent) {
        when (event) {
            is DatabaseEvent.AddNewUser -> {
                addNewUser(event.userUid)
            }
        }
    }


}