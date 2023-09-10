package com.leventsurer.denemelerim.presentation.question_goal_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.domain.use_case.get_exams_topics.GetAytEqualWeightLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.get_exams_topics.GetAytNumericalExamLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.get_exams_topics.GetTytExamLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.question_goal_use_case.AddNewQuestionGoalUseCase
import com.leventsurer.denemelerim.domain.use_case.question_goal_use_case.DeleteQuestionGoalUseCase
import com.leventsurer.denemelerim.domain.use_case.question_goal_use_case.GetQuestionGoalUseCase
import com.leventsurer.denemelerim.domain.use_case.question_goal_use_case.UpdateQuestionGoalUseCase
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class GoalQuestionViewModel @Inject constructor(
    private val addNewQuestionGoalUseCase: AddNewQuestionGoalUseCase,
    private val getQuestionGoalsUseCase: GetQuestionGoalUseCase,
    private val updateQuestionGoalUseCase: UpdateQuestionGoalUseCase,
    private val deleteQuestionGoalUseCase: DeleteQuestionGoalUseCase,
    private val getTytExamLessonsTopicUseCase: GetTytExamLessonsTopicUseCase,
    private val getAytNumericalExamLessonsTopicUseCase: GetAytNumericalExamLessonsTopicUseCase,
    private val getAytEqualWeightExamLessonsTopicUseCase: GetAytEqualWeightLessonsTopicUseCase,
) :
    ViewModel() {


    private val _getQuestionGoalState = mutableStateOf(QuestionGoalState())
    val getQuestionGoalState: State<QuestionGoalState> = _getQuestionGoalState

    private val _addQuestionGoalState = mutableStateOf(QuestionGoalState())
    val addQuestionGoalState: State<QuestionGoalState> = _addQuestionGoalState

    private val _updateGoalState = mutableStateOf(QuestionGoalState())
    val updateGoalState: State<QuestionGoalState> = _updateGoalState


    private val _deleteGoalState = mutableStateOf(QuestionGoalState())
    val deleteGoalState: State<QuestionGoalState> = _deleteGoalState

    private val _tytExamLessonsTopicState = mutableStateOf(QuestionGoalState())
    val tytExamLessonsTopicState:State<QuestionGoalState> = _tytExamLessonsTopicState

    private val _aytNumericalExamLessonsTopicState = mutableStateOf(QuestionGoalState())
    val aytNumericalExamLessonsTopicState:State<QuestionGoalState> = _aytNumericalExamLessonsTopicState

    private val _aytEqualWeightExamLessonsTopicState = mutableStateOf(QuestionGoalState())
    val aytEqualWeightExamLessonsTopicState:State<QuestionGoalState> = _aytEqualWeightExamLessonsTopicState

    private var job: Job? = null
    private fun getQuestionGoals(userUid: String) {
        job?.cancel()
        job = getQuestionGoalsUseCase.executeGetQuestionGoal(userUid).onEach {
            when (it) {
                is Resource.Loading -> {
                    _getQuestionGoalState.value = QuestionGoalState(isLoading = true)
                }

                is Resource.Success -> {
                    Log.e("kontrol","viewmodel success")
                    _getQuestionGoalState.value =
                        QuestionGoalState(result = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _getQuestionGoalState.value =
                        QuestionGoalState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addNewQuestionGoal(questionGoalModel: QuestionGoalModel, userUid: String) {
        job?.cancel()
        job =
            addNewQuestionGoalUseCase.executeAddNewQuestionGoal(questionGoalModel, userUid).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _addQuestionGoalState.value = QuestionGoalState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _addQuestionGoalState.value = QuestionGoalState(isLoading = false,)
                    }

                    is Resource.Error -> {
                        _addQuestionGoalState.value =
                            QuestionGoalState(error = it.message.toString(), isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun updateQuestionGoal(questionGoalModel: QuestionGoalModel, userUid: String) {
        job?.cancel()
        job =
            updateQuestionGoalUseCase.executeUpdateQuestionGoal(questionGoalModel, userUid).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _updateGoalState.value = QuestionGoalState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _updateGoalState.value = QuestionGoalState(isLoading = false,)
                    }

                    is Resource.Error -> {
                        _updateGoalState.value =
                            QuestionGoalState(error = it.message.toString(), isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun deleteQuestionGoal(questionGoalModel: QuestionGoalModel, userUid: String) {
        job?.cancel()
        job =
            deleteQuestionGoalUseCase.executeDeleteQuestionGoal(questionGoalModel, userUid).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _deleteGoalState.value = QuestionGoalState(isLoading = true)
                    }

                    is Resource.Success -> {
                        Log.e("kontrol","Success:")
                        _deleteGoalState.value = QuestionGoalState(isLoading = false)
                    }

                    is Resource.Error -> {
                        Log.e("kontrol","exception:${it.message}")
                        _deleteGoalState.value =
                            QuestionGoalState(error = it.message.toString(), isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun getTytExamLessonsTopics() {
        job?.cancel()
        job = getTytExamLessonsTopicUseCase.executeGetTytExamLessonsTopics().onEach {
            when (it) {
                is Resource.Loading -> {
                    _tytExamLessonsTopicState.value = QuestionGoalState(isLoading = true)
                }

                is Resource.Success -> {
                    _tytExamLessonsTopicState.value =
                        QuestionGoalState(tytTopicsResult = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _tytExamLessonsTopicState.value =
                        QuestionGoalState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAytNumericalExamLessonsTopics() {
        job?.cancel()
        job = getAytNumericalExamLessonsTopicUseCase.executeGetAytNumericalExamLessonsTopics().onEach {
            when (it) {
                is Resource.Loading -> {
                    _aytNumericalExamLessonsTopicState.value = QuestionGoalState(isLoading = true)
                }

                is Resource.Success -> {
                    _aytNumericalExamLessonsTopicState.value =
                        QuestionGoalState(aytNumericalExamLessonsTopic = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _aytNumericalExamLessonsTopicState.value =
                        QuestionGoalState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getAytEqualWeightExamLessonsTopics() {
        job?.cancel()
        job = getAytEqualWeightExamLessonsTopicUseCase.executeGetAytEqualWeightExamLessonsTopics().onEach {
            when (it) {
                is Resource.Loading -> {
                    _aytEqualWeightExamLessonsTopicState.value = QuestionGoalState(isLoading = true)
                }

                is Resource.Success -> {
                    _aytEqualWeightExamLessonsTopicState.value =
                        QuestionGoalState(aytEqualWeightExamLessonsTopic = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _aytEqualWeightExamLessonsTopicState.value =
                        QuestionGoalState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }



    fun onEvent(event: QuestionGoalEvent) {
        when (event) {
            is QuestionGoalEvent.GetQuestionGoals -> {
                getQuestionGoals(event.userUid)
            }

            is QuestionGoalEvent.AddNewQuestionGoal -> {
                addNewQuestionGoal(event.questionGoalModel, event.userUid)
            }

            is QuestionGoalEvent.UpdateQuestionGoal ->{
                updateQuestionGoal(event.questionGoalModel,event.userUid)
            }

            is QuestionGoalEvent.DeleteQuestionGoal->{
                deleteQuestionGoal(event.questionGoalModel,event.userUid)
            }

            is QuestionGoalEvent.GetTytLessonsTopics->{
                getTytExamLessonsTopics()
            }

            is QuestionGoalEvent.GetAytNumericalExamLessonsTopic->{
                getAytNumericalExamLessonsTopics()
            }

            is QuestionGoalEvent.GetAytEqualWeightExamLessonsTopic->{
                getAytEqualWeightExamLessonsTopics()
            }
        }
    }
}