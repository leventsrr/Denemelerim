package com.leventsurer.denemelerim.presentation.common.authentication

import androidx.lifecycle.ViewModel
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository

) : ViewModel() {


    fun logout() = runBlocking {
        authenticationRepository.logout()
    }

    fun deleteAccount() = runBlocking {
        authenticationRepository.deleteAccount()
    }


}