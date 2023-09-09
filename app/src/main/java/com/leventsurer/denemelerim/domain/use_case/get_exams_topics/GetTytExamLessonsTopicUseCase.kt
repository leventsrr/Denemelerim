package com.leventsurer.denemelerim.domain.use_case.get_exams_topics

import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel
import com.leventsurer.denemelerim.domain.repository.GetExamsLessonsTopicApiRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTytExamLessonsTopicUseCase @Inject constructor(
    private val getExamsLessonsTopicApiRepository: GetExamsLessonsTopicApiRepository
) {

    fun executeGetTytExamLessonsTopics(): Flow<Resource<TytExamLessonsTopicsModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = getExamsLessonsTopicApiRepository.getTytExamLessonsTopic()
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }



}