package com.leventsurer.denemelerim.presentation.add_exam_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.use_case.add_ayt_exam.AddAytExamUseCase
import com.leventsurer.denemelerim.domain.use_case.add_tyt_exam.AddTytExamUseCase
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddExamViewModel @Inject constructor(
    private val addTytExamUseCase: AddTytExamUseCase,
    private val addAytExamUseCase: AddAytExamUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddExamState())
    val state: State<AddExamState> = _state


    private var job: Job? = null

    private fun addTytExam(newTytExam:NewTytExamModel) {
        job?.cancel()
        job = addTytExamUseCase.executeAddTytExam(newTytExam).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = AddExamState()
                }
                is Resource.Error -> {
                    AddExamState(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    _state.value = AddExamState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /*private fun addAytExam() {
        job?.cancel()
        job = addAytExamUseCase.executeAddAytExam(newTytExam).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = AddExamState()
                }
                is Resource.Error -> {
                    AddExamState(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    _state.value = AddExamState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }*/


    fun onEvent(event:AddExamEvent){
        when(event){
            is AddExamEvent.addTytExam->{
                //addTytExam()
            }

            is AddExamEvent.addAytExam->{
                //addAytExam()
            }
        }
    }


}