package com.leventsurer.denemelerim.data.remote.dto

import android.net.Uri
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.Gson
import com.leventsurer.denemelerim.util.JsonNavType
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.util.Date

data class NewTytExamModel(
    val examName:String = "",
    val aboutExam:String?= "",
    val turkishCorrect:Int = 0,
    val turkishFalse:Int= 0,
    var turkishNet:Double = 0.0,
    val socialCorrect:Int= 0,
    val socialFalse:Int= 0,
    var socialNet:Double=0.0,
    val mathCorrect:Int= 0,
    val mathFalse:Int= 0,
    var mathNet:Double=0.0,
    val scienceCorrect:Int= 0,
    val scienceFalse:Int= 0,
    var scienceNet:Double=0.0,
    var totalNet:Double = 0.0,
    var totalPoint:Double = 0.0,
    var turkishFalseTopicList: ArrayList<String> = arrayListOf(),
    var geographyFalseTopicList: ArrayList<String> = arrayListOf(),
    var historyFalseTopicList: ArrayList<String> = arrayListOf(),
    var philosophyFalseTopicList: ArrayList<String> = arrayListOf(),
    var religionFalseTopicList: ArrayList<String> = arrayListOf(),
    var mathFalseTopicList: ArrayList<String> = arrayListOf(),
    var physicsFalseTopicList: ArrayList<String> = arrayListOf(),
    var chemicalFalseTopicList: ArrayList<String> = arrayListOf(),
    var biologyFalseTopicList: ArrayList<String> = arrayListOf(),

    @ServerTimestamp
    val examDate: Timestamp = Timestamp.now()
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

class NewTytExamModelArgType : JsonNavType<NewTytExamModel>() {
    override fun fromJsonParse(value: String): NewTytExamModel = Gson().fromJson(value, NewTytExamModel::class.java)

    override fun NewTytExamModel.getJsonParse(): String = Gson().toJson(this)
}
