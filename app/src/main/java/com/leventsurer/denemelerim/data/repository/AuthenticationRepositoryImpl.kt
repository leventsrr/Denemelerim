package com.leventsurer.denemelerim.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.data.remote.AuthenticationApi
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api : AuthenticationApi,
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser {
        return api.signIn(email, password)
    }

    override suspend fun signUp(
        userName: String,
        email: String,
        password: String
    ): FirebaseUser {
        return api.signUp(userName, email, password)
    }

    override suspend fun logout() {
        api.logOut()
    }

    override suspend fun deleteAccount() {
        api.deleteAccount()
    }

}