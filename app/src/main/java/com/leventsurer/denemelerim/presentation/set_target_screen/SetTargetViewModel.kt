package com.leventsurer.denemelerim.presentation.set_target_screen


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.repository.GetUniversitiesAndDepartmentsRepository
import com.leventsurer.denemelerim.domain.use_case.get_universities_and_departments.GetUniversitiesAndDepartmentsUseCase
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
    private val getUniversitiesAndDepartmentsUseCase: GetUniversitiesAndDepartmentsUseCase
) : ViewModel() {
    private val _setTargetState = mutableStateOf(SetTargetState())
    val setTargetState: State<SetTargetState> = _setTargetState

    private val _universitiesAndDepartmentsState = mutableStateOf(SetTargetState())
    val universitiesAndDepartmentsState:State<SetTargetState> = _universitiesAndDepartmentsState
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

    private fun getUniversitiesAndDepartments() {


        getUniversitiesAndDepartmentsUseCase.executeGetUniversitiesAndDepartments().onEach {
            when(it){
                is Resource.Loading->{
                    Log.e("kontrol","uniAndDepart loading")
                    _universitiesAndDepartmentsState.value = SetTargetState(isLoading = true)
                }
                is Resource.Success->{
                    Log.e("kontrol","uniAndDepart success")
                    _universitiesAndDepartmentsState.value = SetTargetState(universitiesAndDepartments = it.data)
                }
                is Resource.Error->{
                    Log.e("kontrol","uniAndDepart error:${it.message}")
                    _universitiesAndDepartmentsState.value = SetTargetState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(event: SetTargetEvent) {
        when (event) {
            is SetTargetEvent.SetTarget -> {
                setTarget(event.university, event.department, event.userUid)
            }
            is SetTargetEvent.GetUniversitiesAndDepartment->{
                getUniversitiesAndDepartments()
            }
        }
    }


}