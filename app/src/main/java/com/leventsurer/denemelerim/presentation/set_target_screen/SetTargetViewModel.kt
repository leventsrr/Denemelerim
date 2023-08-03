package com.leventsurer.denemelerim.presentation.set_target_screen

import com.leventsurer.denemelerim.presentation.login_screen.LoginEvent
import com.leventsurer.denemelerim.presentation.login_screen.LoginState

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.login.SignInUseCase
import com.leventsurer.denemelerim.domain.use_case.set_target.SetTargetUseCase
import com.leventsurer.denemelerim.presentation.register_screen.RegisterEvent
import com.leventsurer.denemelerim.presentation.register_screen.RegisterState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class SetTargetViewModel @Inject constructor(
    private val setTargetUseCase: SetTargetUseCase
): ViewModel() {

    private val _state = mutableStateOf(SetTargetState())
    val state : State<SetTargetState> = _state

    private var job : Job? = null


    private fun setTarget(university:String,department:String){
        job?.cancel()
        job = setTargetUseCase.executeSetTarget( university, department).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = SetTargetState()
                }
                is Resource.Error->{
                    _state.value = SetTargetState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _state.value = SetTargetState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SetTargetEvent){
        when(event){
            is SetTargetEvent.SetTarget->{
                setTarget(event.university,event.department)
            }
        }
    }
}