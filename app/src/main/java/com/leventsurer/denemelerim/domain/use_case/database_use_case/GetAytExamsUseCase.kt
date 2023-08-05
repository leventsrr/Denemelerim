package com.leventsurer.denemelerim.domain.use_case.database_use_case

import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class GetAytExamsUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {



    fun executeGetAytExams(userUid: String): Flow<Resource<ArrayList<NewAytExamModel>>> = flow {
        emit(Resource.Loading())

        val result = databaseRepository.getUserAytExam(userUid)
        if(result!=null){
            emit(Resource.Success(result))
        }else{
            emit(Resource.Error("true"))
        }


    }

}