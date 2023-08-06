package com.leventsurer.denemelerim.presentation.profile_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetUserProfileInfoUseCase
import com.leventsurer.denemelerim.presentation.common.database.DatabaseState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getUserProfileInfoUseCase: GetUserProfileInfoUseCase) :
    ViewModel() {


    private val _getUserProfileInfoState = mutableStateOf(ProfileState())
    val getUserProfileInfoState: State<ProfileState> = _getUserProfileInfoState


    private var job: Job? = null
    private fun getUserProfileInfo(userUid: String){
        job?.cancel()
        job = getUserProfileInfoUseCase.executeGetUserProfileInfo(userUid).onEach {
            when(it){
                is Resource.Loading->{
                    _getUserProfileInfoState.value = ProfileState(isLoading = true)
                }
                is Resource.Success->{
                    _getUserProfileInfoState.value = ProfileState(result = it.data, isLoading = false)
                }

                is Resource.Error-> {
                    _getUserProfileInfoState.value = ProfileState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetUserProfileInfo -> {
                getUserProfileInfo( event.userUid)
            }
        }
    }
}