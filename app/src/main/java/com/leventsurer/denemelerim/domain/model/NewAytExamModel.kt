package com.leventsurer.denemelerim.domain.model

import com.google.firebase.firestore.FieldValue

data class NewAytExamModel(
    val examName :String,
    val aboutExam:String,
    val mathCorrect: Int = 0,
    val mathFalse: Int = 0,
    val physicsCorrect:Int = 0,
    val physicsFalse:Int = 0,
    val chemistryCorrect:Int = 0,
    val chemistryFalse:Int = 0,
    val biologyCorrect:Int= 0,
    val biologyFalse:Int=0,
    val literatureCorrect:Int = 0,
    val literatureFalse:Int = 0,
    val historyCorrect:Int = 0,
    val historyFalse:Int = 0,
    val geographyCorrect:Int=0,
    val geographyFalse:Int=0,
    val historyForSocialCorrect:Int = 0,
    val historyForSocialFalse:Int = 0,
    val geographyForSocialCorrect:Int=0,
    val geographyForSocialFalse:Int=0,
    val philosophyCorrect:Int=0,
    val philosophyFalse:Int=0,
    val religionCorrect:Int = 0,
    val religionFalse:Int=0,
    var numericalPoint:Double = 0.0,
    var equalWeightPoint:Double = 0.0,
    var verbalPoint:Double = 0.0,
    var examDate:FieldValue
)


//fen -> fizik-14,kimya-13 biyoloji-13
//türk dil edebiyatı-> edebiyat-24 tarih-10 coğrafya-6
//sosyal bilimler ->tarih-11 coğrafya-11 felsefe-12 din-6
