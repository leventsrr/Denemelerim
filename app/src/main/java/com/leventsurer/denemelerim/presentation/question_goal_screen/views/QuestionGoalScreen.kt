package com.leventsurer.denemelerim.presentation.question_goal_screen.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.leaderboard_screen.composable.LeaderBoardCardPointRow
import com.leventsurer.denemelerim.presentation.question_goal_screen.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.AddNewQuestionGoalBottomSheet
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.EditGoalBottomSheet
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.QuestionGoalCard
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun QuestionGoalScreen(
    navController: NavController
) {
    val questionGoalViewModel: GoalQuestionViewModel = hiltViewModel()
    val datastoreViewModel: DataStoreViewModel = hiltViewModel()
    val goalQuestionState = questionGoalViewModel.getQuestionGoalState.value
    var showSheet by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
    if (showSheet) {
        AddNewQuestionGoalBottomSheet() {
            showSheet = false
        }
    }

    LaunchedEffect(Unit) {
        questionGoalViewModel.onEvent(
            QuestionGoalEvent.GetQuestionGoals(
                datastoreViewModel.getUserUidFromDataStore()
            )
        )
    }
    Scaffold(
        topBar = { MyTopAppBar(appBarTitle = "Sıralamam", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (goalQuestionState.isLoading) {
                    LottieAnimation(
                        modifier = Modifier.fillMaxWidth(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                } else if (goalQuestionState.result != null && goalQuestionState.result.isEmpty()) {
                    Text(text = "Kayıtlı Bir Hedefiniz Bulunmuyor")
                } else if (!goalQuestionState.result.isNullOrEmpty()) {
                    LazyColumn() {
                        items(goalQuestionState.result.size) { index ->
                            QuestionGoalCard(
                                goalQuestionState.result[index].goalName,
                                goalQuestionState.result[index].goalQuestionQuantity,
                                goalQuestionState.result[index].solvedQuestionQuantity,
                                goalQuestionState.result[index].rightQuestionQuantity,
                                goalQuestionState.result[index].falseQuestionQuantity
                            )
                        }
                    }
                }

            }

        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Secondary,
                onClick = {
                    showSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Goal",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    )
}