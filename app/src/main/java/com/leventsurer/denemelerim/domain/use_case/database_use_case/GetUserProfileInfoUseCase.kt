package com.leventsurer.denemelerim.domain.use_case.database_use_case

import android.util.Log
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.toUserProfileInfoModel
import com.leventsurer.denemelerim.domain.model.UserProfileInfoModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject



class GetUserProfileInfoUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {



    fun executeGetUserProfileInfo(userUid: String): Flow<Resource<UserProfileInfoModel>> = flow {
        emit(Resource.Loading())
        try {
            val result = databaseRepository.getUserProfileInfo(userUid)
            if(result!=null){
                emit(Resource.Success(result.toUserProfileInfoModel()))
            }else{
                emit(Resource.Error("true"))
            }
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }



    }

}