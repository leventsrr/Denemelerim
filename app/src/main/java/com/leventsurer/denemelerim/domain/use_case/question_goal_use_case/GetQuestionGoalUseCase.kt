package com.leventsurer.denemelerim.domain.use_case.question_goal_use_case

import android.util.Log
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject




class GetQuestionGoalUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    fun executeGetQuestionGoal(
        userUid: String
    ): Flow<Resource<ArrayList<QuestionGoalModel>?>> = flow {
        try {
            emit(Resource.Loading())
            val result = databaseRepository.getQuestionGoals(userUid)
            Log.e("kontrol","useCase result:${result}")
            emit(Resource.Success(result))
        } catch (e: Exception) {
            Log.e("kontrol","useCase exception:${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
    }


}
