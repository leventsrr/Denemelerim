package com.leventsurer.denemelerim.domain.use_case.exams_topics

import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject





class GetUserExamLessonsTopicStatusUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun executeGetUserExamLessonsTopicStatusUseCase(userUid:String): Flow<Resource<ArrayList<String>>> = flow {
        try {
            emit(Resource.Loading())
            val result = databaseRepository.getUserExamLessonsTopicStatus(userUid)
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }


}