package com.leventsurer.denemelerim.presentation.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.login.SignInUseCase
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state : State<LoginState> = _state

    private var job : Job? = null

    private fun signIn(email:String,password:String){
        job?.cancel()
        job = signInUseCase.executeSignIn( email, password).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = LoginState(user = it.data)
                }
                is Resource.Error->{
                    _state.value = LoginState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.SignIn->{
                signIn(event.email,event.password)
            }
        }
    }
}