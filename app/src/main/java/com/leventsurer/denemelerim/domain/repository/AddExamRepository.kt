package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel

interface AddExamRepository {

    suspend fun addTytExam(newTytExamModel: NewTytExamModel)
    suspend fun addAytExam(newAytExamModel: NewAytExamModel)
}