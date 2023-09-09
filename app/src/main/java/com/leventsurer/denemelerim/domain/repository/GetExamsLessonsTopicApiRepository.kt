package com.leventsurer.denemelerim.domain.repository

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel

interface GetExamsLessonsTopicApiRepository {

    suspend fun getTytExamLessonsTopic():TytExamLessonsTopicsModel

    suspend fun getAytNumericalExamLessonsTopics():AytNumericalExamLessonsTopicsModel

    suspend fun getAytEqualWeightExamLessonsTopics():AytEqualWeightExamLessonsTopicsModel



}