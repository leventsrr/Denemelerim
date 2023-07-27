package com.leventsurer.denemelerim.domain.use_case.add_tyt_exam

import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.repository.AddExamRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AddTytExamUseCase @Inject constructor(private val addExamRepository: AddExamRepository) {

    fun executeAddTytExam(newTytExamModel: NewTytExamModel): Flow<Resource<String>> = flow {

        val tytExam = calculateExamPoint(newTytExamModel)

        try {
            emit(Resource.Loading())
            val result = addExamRepository.addTytExam(tytExam)
            //emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    private fun calculateExamPoint(newTytExamModel: NewTytExamModel): NewTytExamModel {
        //calculation source : https://egitimevde.com/tyt-ayt-puanlari-nasil-hesaplanir/
        val turkishNet = newTytExamModel.turkishCorrect - (newTytExamModel.turkishFalse/4.0)
        val socialNet = newTytExamModel.socialCorrect - (newTytExamModel.socialFalse/4.0)
        val mathNet = newTytExamModel.mathCorrect - (newTytExamModel.mathFalse/4.0)
        val scienceNet = newTytExamModel.scienceCorrect - (newTytExamModel.scienceFalse/4.0)
        val totalPoint = (turkishNet + mathNet) * 3.3 + (socialNet + scienceNet) * 3.4
        val totalNet = turkishNet + mathNet + scienceNet + socialNet
        newTytExamModel.totalPoint = totalPoint + 100.0
        newTytExamModel.totalNet = totalNet
        return newTytExamModel
    }

}