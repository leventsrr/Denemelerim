package com.leventsurer.denemelerim.domain.use_case.exams_topics

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.domain.repository.GetExamsLessonsTopicApiRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class GetAytEqualWeightLessonsTopicUseCase @Inject constructor(
    private val getExamsLessonsTopicApiRepository: GetExamsLessonsTopicApiRepository
) {

    fun executeGetAytEqualWeightExamLessonsTopics(): Flow<Resource<AytEqualWeightExamLessonsTopicsModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = getExamsLessonsTopicApiRepository.getAytEqualWeightExamLessonsTopics()
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }


}