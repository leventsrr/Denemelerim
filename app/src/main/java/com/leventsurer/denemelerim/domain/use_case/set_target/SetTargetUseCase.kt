package com.leventsurer.denemelerim.domain.use_case.set_target

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.presentation.register_screen.RegisterState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception
import javax.inject.Inject



class SetTargetUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
    ) {


    suspend fun executeSetTarget(university:String,department:String, userUid:String):Flow<Resource<Boolean>> = flow{

        try {
            emit(Resource.Loading())
            databaseRepository.setTarget(university, department, userUid)
            emit(Resource.Success(true))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }

    }

}