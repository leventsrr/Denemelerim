package com.leventsurer.denemelerim.domain.use_case.signUp

import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import com.leventsurer.denemelerim.util.enums.RegisterErrors
import kotlinx.coroutines.Delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val databaseRepository: DatabaseRepository
) {

    fun executeSignUp(
        userName: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): Flow<Resource<FirebaseUser>> = flow {
        val errorMessage = isInputsValueValid(userName, email, password, passwordConfirm)
        if (errorMessage != null) {
            emit(Resource.Error(errorMessage))
        } else {
            try {
                emit(Resource.Loading())
                val result = authenticationRepository.signUp(userName, email, password)
                //databaseRepository.updateUserDisplayName(result.uid, userName)
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }

    }

    private fun isInputsValueValid(
        userName: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): String? {
        if (email.isEmpty()) {
            return RegisterErrors.EMAIL_ERROR.error
        } else if (userName.isEmpty()) {
            return RegisterErrors.USERNAME_ERROR.error
        } else if (password.isEmpty() || passwordConfirm.isEmpty()) {
            return RegisterErrors.PASSWORD_ERROR.error
        } else if (password != passwordConfirm) {
            return RegisterErrors.SAME_PASSWORD_ERROR.error
        }
        return null
    }
}