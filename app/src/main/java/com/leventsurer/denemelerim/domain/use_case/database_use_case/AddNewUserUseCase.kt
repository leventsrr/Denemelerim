package com.leventsurer.denemelerim.domain.use_case.database_use_case

import android.util.Log
import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.model.UserModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class AddNewUserUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {

    val user = UserModel(
        arrayListOf<NewAytExamModel>(), "", 0, 0, "", "",
        0.0, 0.0, arrayListOf<NewTytExamModel>(), "", 0.0,
    )

    fun executeAddNewUser(userUid: String): Flow<Resource<Boolean>> = flow {
        try {
            Log.e("kontrol","addUserUseCase try")
            emit(Resource.Loading())
            databaseRepository.addNewUser(userUid, user)
            emit(Resource.Success(true))
        } catch (e: Exception) {
            Log.e("kontrol","addUserUseCase catch")
            emit(Resource.Error(e.message.toString()))
        }
    }

}