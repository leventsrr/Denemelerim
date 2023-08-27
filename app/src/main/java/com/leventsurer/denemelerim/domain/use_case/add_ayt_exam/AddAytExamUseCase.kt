package com.leventsurer.denemelerim.domain.use_case.add_ayt_exam

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AddAytExamUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {


    fun executeAddAytExam(newAytExamModel: NewAytExamModel, userUid:String): Flow<Resource<String>> = flow {

        val errorMessage = controlInputValues(newAytExamModel = newAytExamModel)
        if (errorMessage == null) {
            try {

                emit(Resource.Loading())
                databaseRepository.addAytExam(calculateExamPoint(newAytExamModel),userUid)
                emit(Resource.Success(""))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        } else {
            emit(Resource.Error(errorMessage))
        }


    }

    //calculation source: https://egitimevde.com/tyt-ayt-puanlari-nasil-hesaplanir/
    private fun calculateExamPoint(newAytExamModel: NewAytExamModel): NewAytExamModel {
        val mathTotalNet = newAytExamModel.mathCorrect - (newAytExamModel.mathFalse / 4.0)
        val physicsTotalNet = newAytExamModel.physicsCorrect - (newAytExamModel.physicsFalse / 4.0)
        val chemistryTotalNet =
            newAytExamModel.chemistryCorrect - (newAytExamModel.chemistryFalse / 4.0)
        val biologyTotalNet = newAytExamModel.biologyCorrect - (newAytExamModel.biologyFalse / 4.0)
        val literatureTotalNet =
            newAytExamModel.literatureCorrect - (newAytExamModel.literatureFalse / 4.0)
        val historyTotalNet = newAytExamModel.historyCorrect - (newAytExamModel.historyFalse / 4.0)
        val geographyTotalNet =
            newAytExamModel.geographyCorrect - (newAytExamModel.geographyFalse / 4.0)
        val historyForSocialTotalNet =
            newAytExamModel.historyForSocialCorrect - (newAytExamModel.historyForSocialFalse / 4.0)
        val geographyForSocialTotalNet =
            newAytExamModel.geographyForSocialCorrect - (newAytExamModel.geographyForSocialFalse / 4.0)
        val philosophyTotalNet =
            newAytExamModel.philosophyCorrect - (newAytExamModel.philosophyFalse / 4.0)
        val religionTotalNet =
            newAytExamModel.religionCorrect - (newAytExamModel.religionFalse / 4.0)


        newAytExamModel.mathNet = mathTotalNet
        newAytExamModel.physicsNet = physicsTotalNet
        newAytExamModel.chemistryNet = chemistryTotalNet
        newAytExamModel.biologyNet = biologyTotalNet
        newAytExamModel.literatureNet = literatureTotalNet
        newAytExamModel.historyNet = historyTotalNet
        newAytExamModel.geographyNet = geographyTotalNet
        newAytExamModel.historyForSocialNet = historyForSocialTotalNet
        newAytExamModel.geographyForSocialNet = geographyForSocialTotalNet
        newAytExamModel.philosophyNet = philosophyTotalNet
        newAytExamModel.religionNet = religionTotalNet

        newAytExamModel.numericalPoint =
            mathTotalNet * 3.0 + physicsTotalNet * 2.85 + chemistryTotalNet * 3.07 + biologyTotalNet * 3.07 + 100.0
        newAytExamModel.equalWeightPoint =
            mathTotalNet * 3.0 + literatureTotalNet * 3.0 + historyTotalNet * 2.8 + geographyTotalNet * 3.33 + 100.0
        newAytExamModel.verbalPoint =
            literatureTotalNet * 3.0 + historyTotalNet * 2.8 + geographyTotalNet * 3.33 + historyForSocialTotalNet * 2.91 + geographyForSocialTotalNet * 2.91 + philosophyTotalNet * 3.0 + religionTotalNet * 3.33 + 100.0

        return newAytExamModel
    }

    private fun controlInputValues(newAytExamModel: NewAytExamModel): String? {
        if (newAytExamModel.mathCorrect + newAytExamModel.mathFalse > 40) {
            return "Matematik değerleri toplamı 40'ı geçemez."
        }
        if (newAytExamModel.physicsCorrect + newAytExamModel.physicsFalse > 14) {
            return "Fizik değerleri toplamı 14'ü geçemez."
        }
        if (newAytExamModel.chemistryCorrect + newAytExamModel.chemistryFalse > 13) {
            return "Kimya değerleri toplamı 13'ü geçemez."
        }
        if (newAytExamModel.literatureCorrect + newAytExamModel.literatureFalse > 24) {
            return "Edebiyat değerleri toplamı 24'ü geçemez."
        }
        if (newAytExamModel.historyCorrect + newAytExamModel.historyFalse > 10) {
            return "Tarih-1 değerleri toplamı 10'u geçemez."
        }
        if (newAytExamModel.geographyCorrect + newAytExamModel.geographyFalse > 6) {
            return "Coğrafya-1 değerleri toplamı 6'yı geçemez."
        }
        if (newAytExamModel.historyForSocialCorrect + newAytExamModel.historyForSocialFalse > 11) {
            return "Tarih-2 değerleri toplamı 11'i geçemez."
        }
        if (newAytExamModel.geographyForSocialCorrect + newAytExamModel.geographyForSocialFalse > 11) {
            return "Coğrafya-2 değerleri toplamı 11'i geçemez."
        }
        if (newAytExamModel.philosophyCorrect + newAytExamModel.philosophyFalse > 12) {
            return "Felsefe değerleri toplamı 12'yi geçemez."
        }
        if (newAytExamModel.religionCorrect + newAytExamModel.religionFalse > 12) {
            return "D.K.V.A.B değerleri toplamı 12'yi geçemez."
        }

        return null
    }


}