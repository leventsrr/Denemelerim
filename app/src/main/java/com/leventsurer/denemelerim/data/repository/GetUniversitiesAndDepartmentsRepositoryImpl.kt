package com.leventsurer.denemelerim.data.repository

import com.leventsurer.denemelerim.data.remote.UniversitiesAndDepartmentsApi
import com.leventsurer.denemelerim.data.remote.dto.UniversitiesAndDepartmentsDto
import com.leventsurer.denemelerim.domain.repository.GetUniversitiesAndDepartmentsRepository
import javax.inject.Inject

class GetUniversitiesAndDepartmentsRepositoryImpl @Inject constructor(
    private val universitiesAndDepartmentsApi: UniversitiesAndDepartmentsApi
) : GetUniversitiesAndDepartmentsRepository {
    override suspend fun getUniversitiesAndDepartments(): List<UniversitiesAndDepartmentsDto> {
        return universitiesAndDepartmentsApi.getUniversitiesAndDepartments()
    }
}