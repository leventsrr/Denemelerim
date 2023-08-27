package com.leventsurer.denemelerim.presentation.leaderboard_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.leaderboard_use_case.GetLeaderboardUseCase
import com.leventsurer.denemelerim.presentation.login_screen.LoginEvent
import com.leventsurer.denemelerim.presentation.login_screen.LoginState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(private val leaderboardUseCase: GetLeaderboardUseCase) :
    ViewModel() {


    private val _leaderboardState = mutableStateOf(LeaderboardState())
    val leaderboardState : State<LeaderboardState> = _leaderboardState

    private var job : Job? = null

    private fun getUsersToLeaderboard(universityName:String,departmentName:String){
        job?.cancel()
        job = leaderboardUseCase.getUsersToLeaderboard( universityName, departmentName).onEach {
            when(it){
                is Resource.Success ->{
                    Log.e("kontrol","vm users:${it.data}")
                    _leaderboardState.value = LeaderboardState(users = it.data, isLoading = false)
                }
                is Resource.Error->{
                    _leaderboardState.value = LeaderboardState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _leaderboardState.value = LeaderboardState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: LeaderboardEvent){
        when(event){
            is LeaderboardEvent.GetUsersToLeaderboard->{
                getUsersToLeaderboard(event.universityName,event.departmentName)
            }
        }
    }

}