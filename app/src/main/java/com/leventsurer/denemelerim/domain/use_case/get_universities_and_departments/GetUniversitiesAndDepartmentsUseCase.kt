package com.leventsurer.denemelerim.domain.use_case.get_universities_and_departments

import android.util.Log
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.UniversitiesAndDepartmentsDto
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.data.remote.dto.toUniversitiesAndDepartmentsModel
import com.leventsurer.denemelerim.domain.model.UniversitiesAndDepartmentsModel
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.domain.repository.GetUniversitiesAndDepartmentsRepository
import com.leventsurer.denemelerim.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class GetUniversitiesAndDepartmentsUseCase @Inject constructor(private val getUniversitiesAndDepartmentsRepository: GetUniversitiesAndDepartmentsRepository) {


    fun executeGetUniversitiesAndDepartments(): Flow<Resource<List<UniversitiesAndDepartmentsModel>>> =
        flow {
            try {

                emit(Resource.Loading())
                val result = getUniversitiesAndDepartmentsRepository.getUniversitiesAndDepartments()
                val universitiesAndDepartmentsModels =
                    arrayListOf<UniversitiesAndDepartmentsModel>()
                for (universityAndDepartment in result) {
                    universitiesAndDepartmentsModels.add(
                        UniversitiesAndDepartmentsModel(
                            universityName = universityAndDepartment.university,
                            departmentName = universityAndDepartment.name,
                            point_type = universityAndDepartment.point_type
                        )
                    )
                }

                emit(Resource.Success(universitiesAndDepartmentsModels))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }

}