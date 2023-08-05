package com.leventsurer.denemelerim.presentation.register_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.use_case.signUp.SignUpUseCase
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
): ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state : State<RegisterState> = _state

    private var job : Job? = null


    private fun signUp(userName:String,email:String,password:String,passwordConfirm:String){
        job?.cancel()
        job = signUpUseCase.executeSignUp(userName, email, password,passwordConfirm).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = RegisterState(user = it.data)
                }
                is Resource.Error->{
                    _state.value = RegisterState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _state.value = RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event:RegisterEvent){
        when(event){
            is RegisterEvent.SignUp->{
                signUp(event.userName,event.email,event.password,event.passwordConfirm)
            }
        }
    }




}