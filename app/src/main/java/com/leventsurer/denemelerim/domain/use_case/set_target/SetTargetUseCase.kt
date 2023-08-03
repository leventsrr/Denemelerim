package com.leventsurer.denemelerim.domain.use_case.set_target

import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.SetTargetRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject



class SetTargetUseCase @Inject constructor(private val setTargetRepository: SetTargetRepository) {

    fun executeSetTarget(university:String,department:String): Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading())
            val result = setTargetRepository.setTarget(university, department)
            emit(Resource.Success(result))
        }catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

}