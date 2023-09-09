package com.leventsurer.denemelerim.data.remote

import com.leventsurer.denemelerim.data.remote.dto.AytEqualWeightExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.AytNumericalExamLessonsTopicsModel
import com.leventsurer.denemelerim.data.remote.dto.TytExamLessonsTopicsModel
import retrofit2.http.GET



interface ExamsLessonsTopicsApi {

    @GET("tytDersKonulari.txt")
    suspend fun getTytExamLessonTopics() : TytExamLessonsTopicsModel

    @GET("aytSayisalKonulari.txt")
    suspend fun getAytNumericalExamLessonTopics() : AytNumericalExamLessonsTopicsModel

    @GET("aytEsitAgirlikKonulari.txt")
    suspend fun getAytEqualWeightExamLessonTopics() : AytEqualWeightExamLessonsTopicsModel



}