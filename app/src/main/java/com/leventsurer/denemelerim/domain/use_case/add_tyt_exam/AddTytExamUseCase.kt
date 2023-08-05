package com.leventsurer.denemelerim.domain.use_case.add_tyt_exam

import android.util.Log
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.repository.AddExamRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AddTytExamUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {

    fun executeAddTytExam(newTytExamModel: NewTytExamModel,userUid:String): Flow<Resource<String>> = flow {

        val errorMessage = controlInputValues(newTytExamModel)
        if(errorMessage == null){
            val tytExam = calculateExamPoint(newTytExamModel)

            try {
                emit(Resource.Loading())
                databaseRepository.addTytExam(tytExam,userUid)
                emit(Resource.Success(""))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }else{
            emit(Resource.Error(errorMessage))
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
        newTytExamModel.turkishNet = turkishNet
        newTytExamModel.mathNet = mathNet
        newTytExamModel.socialNet = socialNet
        newTytExamModel.scienceNet = scienceNet
        newTytExamModel.totalPoint = totalPoint + 100.0
        newTytExamModel.totalNet = totalNet
        return newTytExamModel
    }

    private fun controlInputValues(newTytExamModel: NewTytExamModel):String?{
        if(newTytExamModel.turkishCorrect + newTytExamModel.turkishFalse > 40){
            return "Türkçe değerleri toplamı 40'ı geçemez"
        }else if (newTytExamModel.mathCorrect + newTytExamModel.mathFalse > 40){
            return "Matematik değerleri toplamı 40'ı geçemez"
        }else if (newTytExamModel.socialCorrect + newTytExamModel.socialFalse > 20){
            return "Sosyal değerleri toplamı 20'yi geçemez"
        }else if(newTytExamModel.scienceCorrect + newTytExamModel.scienceFalse > 20){
            return "Fen değerleri toplamı 20'yi geçemez"
        }
        return null
    }

}