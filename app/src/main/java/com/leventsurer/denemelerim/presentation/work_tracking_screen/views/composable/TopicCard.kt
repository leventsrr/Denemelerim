package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals.QuestionGoalEvent

@Composable
fun TopicCard(topicName: String,isCheckedBefore:Boolean) {


    val goalQuestionViewModel: GoalQuestionViewModel = hiltViewModel()
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
    var isChecked by remember {
        mutableStateOf(isCheckedBefore)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isChecked = !isChecked
                Log.e("kontrol","status:${isChecked}")
                goalQuestionViewModel.onEvent(
                    QuestionGoalEvent.ChangeExamLessonTopicStatus(
                        topicName,
                        isChecked,
                        dataStoreViewModel.getUserUidFromDataStore()
                    )
                )

            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = topicName)
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = !isChecked
                    QuestionGoalEvent.ChangeExamLessonTopicStatus(
                        topicName,
                        isChecked,
                        dataStoreViewModel.getUserUidFromDataStore()
                    )
                })


        }
        Divider()
    }
}

