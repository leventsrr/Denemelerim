package com.leventsurer.denemelerim.data.remote

import com.leventsurer.denemelerim.data.remote.dto.UniversitiesAndDepartmentsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesAndDepartmentsApi {

    @GET("universitiesAnddepartments/main/departments.txt")
    suspend fun getUniversitiesAndDepartments() : List<UniversitiesAndDepartmentsDto>


}