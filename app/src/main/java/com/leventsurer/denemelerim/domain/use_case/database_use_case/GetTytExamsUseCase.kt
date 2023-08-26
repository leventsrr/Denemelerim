package com.leventsurer.denemelerim.domain.use_case.database_use_case

import android.util.Log
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class GetTytExamsUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {



    fun executeGetTytExams(userUid: String): Flow<Resource<ArrayList<NewTytExamModel>>> = flow {
            emit(Resource.Loading())
            val result = databaseRepository.getUserTytExam(userUid)
            if(result!=null){
                emit(Resource.Success(result))
            }else{
                emit(Resource.Error("true"))
            }


    }

}