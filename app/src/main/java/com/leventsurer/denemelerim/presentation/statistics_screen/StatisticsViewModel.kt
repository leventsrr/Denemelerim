package com.leventsurer.denemelerim.presentation.statistics_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetAytExamsUseCase
import com.leventsurer.denemelerim.domain.use_case.database_use_case.GetTytExamsUseCase
import com.leventsurer.denemelerim.presentation.login_screen.LoginState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getTytExamsUseCase: GetTytExamsUseCase,
    private val getAytExamsUseCase: GetAytExamsUseCase
) :ViewModel() {


    private val _statisticsState = mutableStateOf(StatisticsState())
    val statisticsState : State<StatisticsState> = _statisticsState

    private var job : Job? = null

    private fun getTytExams(userUid:String){
        job?.cancel()
        job = getTytExamsUseCase.executeGetTytExams(userUid).onEach {
            when(it){
                is Resource.Success ->{
                    _statisticsState.value = StatisticsState(tytExams = it.data)
                }
                is Resource.Error->{
                    _statisticsState.value = StatisticsState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _statisticsState.value = StatisticsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAytExams(userUid:String){
        job?.cancel()
        job = getAytExamsUseCase.executeGetAytExams(userUid).onEach {
            when(it){
                is Resource.Success ->{
                    _statisticsState.value = StatisticsState(aytExams = it.data)
                }
                is Resource.Error->{
                    _statisticsState.value = StatisticsState(error = it.message ?: "Error")
                }
                is Resource.Loading->{
                    _statisticsState.value = StatisticsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: StatisticsEvent){
        when(event){
            is StatisticsEvent.GetTytExams->{
                getTytExams(event.userUid)
            }
            is StatisticsEvent.GetAytExams->{
                getAytExams(event.userUid)
            }
        }
    }
}