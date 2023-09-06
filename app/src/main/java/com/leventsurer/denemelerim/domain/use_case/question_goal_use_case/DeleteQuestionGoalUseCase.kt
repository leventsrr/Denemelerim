package com.leventsurer.denemelerim.domain.use_case.question_goal_use_case

import android.util.Log
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class DeleteQuestionGoalUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    fun executeDeleteQuestionGoal(
        questionGoalModel: QuestionGoalModel,
        userUid: String
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            databaseRepository.deleteQuestionGoal( userUid,questionGoalModel)
            Log.e("kontrol","usecase try")
            emit(Resource.Success(""))
        } catch (e: Exception) {
            Log.e("kontrol","usecase cath:${e.cause}")
            emit(Resource.Error(e.message.toString()))
        }
    }


}