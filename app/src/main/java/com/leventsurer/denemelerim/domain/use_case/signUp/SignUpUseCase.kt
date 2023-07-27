package com.leventsurer.denemelerim.domain.use_case.signUp

import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    fun executeSignUp(userName:String,email:String,password:String):Flow<Resource<FirebaseUser>> = flow{
        try {
            emit(Resource.Loading())
            val result = authenticationRepository.signUp(userName, email, password)
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}