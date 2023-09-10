package com.leventsurer.denemelerim.domain.use_case.get_exams_topics

import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel
import com.leventsurer.denemelerim.domain.repository.GetExamsLessonsTopicApiRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAytNumericalExamLessonsTopicUseCase @Inject constructor(
    private val getExamsLessonsTopicApiRepository: GetExamsLessonsTopicApiRepository
) {

    fun executeGetAytNumericalExamLessonsTopics(): Flow<Resource<AytNumericalExamLessonsTopicsModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = getExamsLessonsTopicApiRepository.getAytNumericalExamLessonsTopics()
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }


}