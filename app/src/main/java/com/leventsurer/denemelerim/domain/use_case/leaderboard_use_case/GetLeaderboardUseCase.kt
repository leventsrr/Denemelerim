package com.leventsurer.denemelerim.domain.use_case.leaderboard_use_case

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.domain.repository.GetUniversitiesAndDepartmentsRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject



class GetLeaderboardUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val getUniversitiesAndDepartmentsRepository: GetUniversitiesAndDepartmentsRepository
    ) {

    fun getUsersToLeaderboard(universityName:String,departmentName:String): Flow<Resource<List<UserModel>>> = flow{
        try {
            emit(Resource.Loading())
            val pointType = getUniversitiesAndDepartmentsRepository.getUniversitiesAndDepartmentPointType(universityName, departmentName)
            Log.e("kontrol","GetLeaderboardUseCase-pointType :${pointType}")
            val result = databaseRepository.getUsersToLeaderboard(universityName, departmentName,pointType)
            Log.e("kontrol","GetLeaderboardUseCase-result :${result}")
            emit(Resource.Success(result))
        }catch (e: Exception){
            Log.e("kontrol","excepiton:${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
    }

}