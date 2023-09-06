package com.leventsurer.denemelerim.domain.use_case.database_use_case

import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUserAccountUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {
    fun executeDeleteUserAccount(userUid: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val result = databaseRepository.deleteUserAccount(userUid)

            emit(Resource.Success(true))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}