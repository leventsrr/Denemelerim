package com.leventsurer.denemelerim.presentation.add_exam_screen


sealed class AddExamEvent{

    data class addTytExam(val email:String,val password:String):AddExamEvent()
    data class addAytExam(val email:String,val password:String):AddExamEvent()

}

