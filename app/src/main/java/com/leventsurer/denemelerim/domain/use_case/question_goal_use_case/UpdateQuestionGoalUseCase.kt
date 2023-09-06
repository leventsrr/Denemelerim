package com.leventsurer.denemelerim.domain.use_case.question_goal_use_case

import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class UpdateQuestionGoalUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    fun executeUpdateQuestionGoal(
        questionGoalModel: QuestionGoalModel,
        userUid: String
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            databaseRepository.updateQuestionGoal(userUid,questionGoalModel)
            emit(Resource.Success(""))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }


}