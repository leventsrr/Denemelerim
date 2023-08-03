package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel

interface AddExamRepository {

    suspend fun addTytExam(newTytExamModel: NewTytExamModel)
    suspend fun addAytExam(newAytExamModel: NewAytExamModel)
}