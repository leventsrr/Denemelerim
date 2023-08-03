package com.leventsurer.denemelerim.presentation.add_exam_screen

import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel


sealed class AddExamEvent{

    data class AddTytExam(val newTytExamModel: NewTytExamModel):AddExamEvent()
    data class AddAytExam(val newAytExamModel: NewAytExamModel):AddExamEvent()

}

