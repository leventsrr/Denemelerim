package com.leventsurer.denemelerim.presentation.set_target_screen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.set_target.SetTargetUseCase
import com.leventsurer.denemelerim.presentation.register_screen.RegisterEvent
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SetTargetViewModel @Inject constructor(
    private val setTargetUseCase: SetTargetUseCase,
) : ViewModel() {
    private val _setTargetState = mutableStateOf(SetTargetState())
    val setTargetState: State<SetTargetState> = _setTargetState


    private var job: Job? = null
    private fun setTarget(university: String, department: String, userUid: String) = runBlocking {
        job?.cancel()
        job = setTargetUseCase.executeSetTarget(university, department, userUid).onEach {
            when(it){
                is Resource.Loading->{
                    _setTargetState.value = SetTargetState(isLoading = true)
                }
                is Resource.Success->{
                    _setTargetState.value = SetTargetState(result = it.data)
                }

                is Resource.Error-> {
                    _setTargetState.value = SetTargetState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)

    }


    fun onEvent(event: SetTargetEvent) {
        when (event) {
            is SetTargetEvent.SetTarget -> {
                setTarget(event.university, event.department, event.userUid)
            }
        }
    }


}