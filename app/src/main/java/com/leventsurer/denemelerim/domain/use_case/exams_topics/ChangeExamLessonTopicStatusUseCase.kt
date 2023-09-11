package com.leventsurer.denemelerim.domain.use_case.exams_topics
import android.util.Log
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ChangeExamLessonTopicStatusUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun executeChangeExamLessonTopicStatusUseCase(topicName:String,isDone:Boolean,userUid: String): Flow<Resource<Boolean>> = flow {
        try {
            Log.e("kontrol","status useCase:${isDone}")
            emit(Resource.Loading())
            databaseRepository.changeExamLessonTopicStatus(topicName, isDone, userUid)
            emit(Resource.Success(true))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }


}