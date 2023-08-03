package com.leventsurer.denemelerim.data.repository

import com.leventsurer.denemelerim.data.remote.SetTargetApi
import com.leventsurer.denemelerim.domain.repository.SetTargetRepository
import javax.inject.Inject

class SetTargetRepositoryImpl @Inject constructor(
    private val setTargetApi: SetTargetApi
) : SetTargetRepository {

    override suspend fun setTarget(university: String, department: String):Boolean {
        return setTargetApi.setTarget(university, department)
    }
}