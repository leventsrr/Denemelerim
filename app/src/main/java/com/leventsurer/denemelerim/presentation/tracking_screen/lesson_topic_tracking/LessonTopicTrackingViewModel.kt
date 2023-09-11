package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.use_case.exams_topics.ChangeExamLessonTopicStatusUseCase
import com.leventsurer.denemelerim.domain.use_case.exams_topics.GetAytEqualWeightLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.exams_topics.GetAytNumericalExamLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.exams_topics.GetTytExamLessonsTopicUseCase
import com.leventsurer.denemelerim.domain.use_case.exams_topics.GetUserExamLessonsTopicStatusUseCase
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalState
import com.leventsurer.denemelerim.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class LessonTopicTrackingViewModel @Inject constructor(
    private val getTytExamLessonsTopicUseCase: GetTytExamLessonsTopicUseCase,
    private val getAytNumericalExamLessonsTopicUseCase: GetAytNumericalExamLessonsTopicUseCase,
    private val getAytEqualWeightExamLessonsTopicUseCase: GetAytEqualWeightLessonsTopicUseCase,
    private val changeExamLessonTopicStatusUseCase: ChangeExamLessonTopicStatusUseCase,
    private val getUserExamLessonsTopicStatusUseCase: GetUserExamLessonsTopicStatusUseCase,
) :ViewModel(){

    private val _tytExamLessonsTopicState = mutableStateOf(LessonTopicTrackingState())
    val tytExamLessonsTopicState: State<LessonTopicTrackingState> = _tytExamLessonsTopicState

    private val _aytNumericalExamLessonsTopicState = mutableStateOf(LessonTopicTrackingState())
    val aytNumericalExamLessonsTopicState: State<LessonTopicTrackingState> =
        _aytNumericalExamLessonsTopicState

    private val _aytEqualWeightExamLessonsTopicState = mutableStateOf(LessonTopicTrackingState())
    val aytEqualWeightExamLessonsTopicState: State<LessonTopicTrackingState> =
        _aytEqualWeightExamLessonsTopicState

    private val _changeExamLessonTopicStatusState = mutableStateOf(LessonTopicTrackingState())
    val changeExamLessonTopicStatusState: State<LessonTopicTrackingState> =
        _changeExamLessonTopicStatusState

    private val _getUserExamLessonsTopicStatus = mutableStateOf(LessonTopicTrackingState())
    val getUserExamLessonsTopicStatus: State<LessonTopicTrackingState> =
        _getUserExamLessonsTopicStatus

    private fun getTytExamLessonsTopics() {
        job?.cancel()
        job = getTytExamLessonsTopicUseCase.executeGetTytExamLessonsTopics().onEach {
            when (it) {
                is Resource.Loading -> {
                    _tytExamLessonsTopicState.value = LessonTopicTrackingState(isLoading = true)
                }

                is Resource.Success -> {
                    _tytExamLessonsTopicState.value =
                        LessonTopicTrackingState(tytTopicsResult = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _tytExamLessonsTopicState.value =
                        LessonTopicTrackingState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAytNumericalExamLessonsTopics() {
        job?.cancel()
        job = getAytNumericalExamLessonsTopicUseCase.executeGetAytNumericalExamLessonsTopics()
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        _aytNumericalExamLessonsTopicState.value =
                            LessonTopicTrackingState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _aytNumericalExamLessonsTopicState.value =
                            LessonTopicTrackingState(
                                aytNumericalExamLessonsTopic = it.data,
                                isLoading = false
                            )
                    }

                    is Resource.Error -> {
                        _aytNumericalExamLessonsTopicState.value =
                            LessonTopicTrackingState(error = it.message.toString(), isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun getAytEqualWeightExamLessonsTopics() {
        job?.cancel()
        job = getAytEqualWeightExamLessonsTopicUseCase.executeGetAytEqualWeightExamLessonsTopics()
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        _aytEqualWeightExamLessonsTopicState.value =
                            LessonTopicTrackingState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _aytEqualWeightExamLessonsTopicState.value =
                            LessonTopicTrackingState(
                                aytEqualWeightExamLessonsTopic = it.data,
                                isLoading = false
                            )
                    }

                    is Resource.Error -> {
                        _aytEqualWeightExamLessonsTopicState.value =
                            LessonTopicTrackingState(error = it.message.toString(), isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)
    }




    private fun changeExamLessonTopicStatus(topicName: String, isDone: Boolean, userUid: String) {
        Log.e("kontrol","status viewModel:${isDone}")
        job?.cancel()
        job = changeExamLessonTopicStatusUseCase.executeChangeExamLessonTopicStatusUseCase(
            topicName,
            isDone,
            userUid
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _changeExamLessonTopicStatusState.value = LessonTopicTrackingState(isLoading = true)
                }

                is Resource.Success -> {
                    _changeExamLessonTopicStatusState.value =
                        LessonTopicTrackingState(
                            changeExamLessonTopicStatusResult = it.data,
                            isLoading = false
                        )
                }

                is Resource.Error -> {
                    _changeExamLessonTopicStatusState.value =
                        LessonTopicTrackingState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
    private var job: Job? = null
    private fun getExamLessonTopicStatus(userUid: String) {
        job?.cancel()
        job = getUserExamLessonsTopicStatusUseCase.executeGetUserExamLessonsTopicStatusUseCase(userUid).onEach {
            when (it) {
                is Resource.Loading -> {
                    _getUserExamLessonsTopicStatus.value = LessonTopicTrackingState(isLoading = true)
                }

                is Resource.Success -> {
                    _getUserExamLessonsTopicStatus.value =
                        LessonTopicTrackingState(
                            userExamLessonsTopicStatusResult = it.data,
                            isLoading = false
                        )
                }

                is Resource.Error -> {
                    _getUserExamLessonsTopicStatus.value =
                        LessonTopicTrackingState(error = it.message.toString(), isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: LessonTopicTrackingEvent) {
        when (event) {
            is LessonTopicTrackingEvent.ChangeExamLessonTopicStatus -> {
                changeExamLessonTopicStatus(event.topicName, event.isDone, event.userUid)
            }

            is LessonTopicTrackingEvent.GetExamLessonTopicStatus -> {
                getExamLessonTopicStatus(event.userUid)
            }

            is LessonTopicTrackingEvent.GetTytLessonsTopics -> {
                getTytExamLessonsTopics()
            }

            is LessonTopicTrackingEvent.GetAytNumericalExamLessonsTopic -> {
                getAytNumericalExamLessonsTopics()
            }

            is LessonTopicTrackingEvent.GetAytEqualWeightExamLessonsTopic -> {
                getAytEqualWeightExamLessonsTopics()
            }
        }
    }

}