package com.leventsurer.denemelerim.presentation.add_exam_screen

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel


sealed class AddExamEvent{

    data class AddTytExam(val newTytExamModel: NewTytExamModel, val userUid:String):AddExamEvent()
    data class AddAytExam(val newAytExamModel: NewAytExamModel, val userUid: String):AddExamEvent()

}

