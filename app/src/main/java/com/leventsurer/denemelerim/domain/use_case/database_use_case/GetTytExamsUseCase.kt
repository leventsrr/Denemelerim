package com.leventsurer.denemelerim.domain.use_case.database_use_case

import android.util.Log
import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.model.UserModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject



class GetTytExamsUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {



    fun executeGetTytExams(userUid: String): Flow<Resource<ArrayList<NewTytExamModel>>> = flow {
            emit(Resource.Loading())
            val result = databaseRepository.getUserTytExam(userUid)
            if(result!=null){
                emit(Resource.Success(result))
            }else{
                Log.e("kontrol",    "use case else içi")
                emit(Resource.Error("true"))
            }


    }

}