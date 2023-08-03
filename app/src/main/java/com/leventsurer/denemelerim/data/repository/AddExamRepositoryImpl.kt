package com.leventsurer.denemelerim.data.repository

import com.leventsurer.denemelerim.data.remote.AddNewExamApi
import com.leventsurer.denemelerim.domain.model.NewAytExamModel
import com.leventsurer.denemelerim.domain.model.NewTytExamModel
import com.leventsurer.denemelerim.domain.repository.AddExamRepository
import javax.inject.Inject

class AddExamRepositoryImpl @Inject constructor(
    private val addNewExamApi:AddNewExamApi
) : AddExamRepository {

    override suspend fun addTytExam(newTytExamModel: NewTytExamModel) {
        addNewExamApi.addNewTytExam(newTytExamModel)
    }

    override suspend fun addAytExam(newAytExamModel: NewAytExamModel) {
        addNewExamApi.addNewAytExam(newAytExamModel)
    }

}