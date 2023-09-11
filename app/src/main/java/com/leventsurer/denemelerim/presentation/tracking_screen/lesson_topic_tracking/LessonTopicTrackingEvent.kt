package com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking

import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalEvent

sealed class LessonTopicTrackingEvent {

    data class ChangeExamLessonTopicStatus(val topicName:String,val isDone:Boolean,val userUid: String):
        LessonTopicTrackingEvent()
    data class GetExamLessonTopicStatus(val userUid: String): LessonTopicTrackingEvent()

    object GetTytLessonsTopics: LessonTopicTrackingEvent()

    object GetAytNumericalExamLessonsTopic: LessonTopicTrackingEvent()
    object GetAytEqualWeightExamLessonsTopic: LessonTopicTrackingEvent()
}