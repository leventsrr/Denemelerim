package com.leventsurer.denemelerim.presentation.work_tracking_screen.views.question_goals

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.work_tracking_screen.views.composable.QuestionGoalCard

@Composable
fun QuestionGoalsScreen() {
    val questionGoalViewModel: GoalQuestionViewModel = hiltViewModel()
    val datastoreViewModel: DataStoreViewModel = hiltViewModel()
    val goalQuestionState = questionGoalViewModel.getQuestionGoalState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
    var goalList by remember { mutableStateOf(emptyList<QuestionGoalModel>()) }
    LaunchedEffect(Unit) {
        questionGoalViewModel.onEvent(
            QuestionGoalEvent.GetQuestionGoals(
                datastoreViewModel.getUserUidFromDataStore()
            )
        )
    }
    if (goalQuestionState.isLoading) {
        LottieAnimation(
            modifier = Modifier.fillMaxWidth(),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    } else if (goalQuestionState.result != null && goalQuestionState.result.isEmpty()) {
        Text(
            text = "Kayıtlı Bir Soru Hedefiniz Bulunmuyor",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    } else if (!goalQuestionState.result.isNullOrEmpty()) {
        goalList = goalQuestionState.result.toList()
        LazyColumn() {
            items(goalList.size) { index ->
                val questionGoalModel = QuestionGoalModel(
                    goalList[index].goalName,
                    goalList[index].goalQuestionQuantity,
                    goalList[index].solvedQuestionQuantity,
                    goalList[index].rightQuestionQuantity,
                    goalList[index].falseQuestionQuantity,
                    goalList[index].emptyQuestionQuantity
                )
                QuestionGoalCard(
                    questionGoalModel,
                    deleteItem = {
                        goalList = goalList.toMutableList().apply { removeAt(index) }
                    }
                )
            }
        }
    }
}