package com.leventsurer.denemelerim.data.remote.dto

import com.leventsurer.denemelerim.domain.model.UniversitiesAndDepartmentsModel


data class UniversitiesAndDepartmentsDto(
    val base_score_of_last_year: Double,
    val college: String,
    val name: String,
    val order_of_success_of_last_year: Int,
    val point_type: String,
    val quota: Int,
    val quota_of_top_student_of_the_school: Int,
    val special_conditions: Int?,
    val university: String,
    val years: Int
)


fun UniversitiesAndDepartmentsDto.toUniversitiesAndDepartmentsModel() : UniversitiesAndDepartmentsModel {
    return UniversitiesAndDepartmentsModel(universityName = university, departmentName = name)
}
