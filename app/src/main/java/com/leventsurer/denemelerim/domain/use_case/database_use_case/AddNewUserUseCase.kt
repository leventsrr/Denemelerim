package com.leventsurer.denemelerim.domain.use_case.database_use_case

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class AddNewUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {

    val user = UserModel(
        arrayListOf<NewAytExamModel>(),
        "",
        0,
        0,
        "",
        "",
        0.0,
        0.0,
        totalNumericalPoints = 0.0,
        totalVerbalPoints = 0.0,
        arrayListOf<NewTytExamModel>(),
        "",
        0.0,
        tytNetList = arrayListOf(),
        aytNumericalNetList = arrayListOf(),
        aytEqualWeightNetList = arrayListOf(),
        aytVerbalNetList = arrayListOf(),
        questionGoals = arrayListOf()
    )

    fun executeAddNewUser(userUid: String, userName: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            databaseRepository.addNewUser(userUid, user, userName)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

}