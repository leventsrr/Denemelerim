package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.data.remote.dto.UniversitiesAndDepartmentsDto

interface GetUniversitiesAndDepartmentsRepository {

    suspend fun getUniversitiesAndDepartments():List<UniversitiesAndDepartmentsDto>
}