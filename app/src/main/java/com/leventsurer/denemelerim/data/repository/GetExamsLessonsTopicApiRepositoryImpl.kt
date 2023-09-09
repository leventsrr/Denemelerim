package com.leventsurer.denemelerim.data.repository

import com.leventsurer.denemelerim.data.remote.ExamsLessonsTopicsApi
import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel
import com.leventsurer.denemelerim.domain.repository.GetExamsLessonsTopicApiRepository
import javax.inject.Inject

class GetExamsLessonsTopicApiRepositoryImpl @Inject constructor(
    private val examsLessonsTopicsApi: ExamsLessonsTopicsApi
) :GetExamsLessonsTopicApiRepository {
    override suspend fun getTytExamLessonsTopic(): TytExamLessonsTopicsModel {
        return examsLessonsTopicsApi.getTytExamLessonTopics()
    }

    override suspend fun getAytNumericalExamLessonsTopics(): AytNumericalExamLessonsTopicsModel {
        return examsLessonsTopicsApi.getAytNumericalExamLessonTopics()
    }

    override suspend fun getAytEqualWeightExamLessonsTopics(): AytEqualWeightExamLessonsTopicsModel {
        return examsLessonsTopicsApi.getAytEqualWeightExamLessonTopics()
    }
}