package com.leventsurer.denemelerim.presentation.tracking_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.views.composable.AddNewQuestionGoalBottomSheet
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.LessonsTopicScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.views.QuestionGoalsScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.study_goals.views.StudyGoalScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.study_goals.views.composable.Timer

@Composable
fun QuestionGoalScreen(
    navController: NavController,
) {


    var showSheet by remember { mutableStateOf(false) }

    var state by remember { mutableStateOf(0) }
    val tabTitles = arrayListOf("Konu Takibim","Çalışma Takibim","Soru Hedeflerim")
    if (showSheet) {
        AddNewQuestionGoalBottomSheet() {
            showSheet = false
        }
    }
    var isInQuestionGoalScreen by remember {
        mutableStateOf(false)
    }

    var isInStudyGoalScreen by remember {
        mutableStateOf(false)
    }

    var isTimerActive by remember {
        mutableStateOf(false)
    }
    if(isTimerActive){
        Timer()
    }else{
        Scaffold(
            topBar = {
                MyTopAppBar(appBarTitle = "Hedeflerim", navController = navController) },
            content = { padding ->
                Column(
                    modifier = Modifier.padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TabRow(selectedTabIndex = state) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = state == index,
                                onClick = { state = index },
                                text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                                selectedContentColor = Secondary,
                                unselectedContentColor = Primary
                            )
                        }
                    }
                    when (state) {
                        0 -> {
                            isInStudyGoalScreen = false
                            isInQuestionGoalScreen = false
                            LessonsTopicScreen()
                        }
                        1 -> {
                            isInQuestionGoalScreen = false
                            isInStudyGoalScreen = true
                            StudyGoalScreen()

                        }
                        2 -> {
                            isInStudyGoalScreen = false
                            isInQuestionGoalScreen = true
                            QuestionGoalsScreen()

                        }
                    }



                }

            },
            floatingActionButton = {
                if(isInQuestionGoalScreen){
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
                }else if(isInStudyGoalScreen){
                    FloatingActionButton(
                        containerColor = Secondary,
                        onClick = {
                            isTimerActive = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_time_24),
                            contentDescription = "asdasd",
                            tint = Color.White
                        )
                    }
                }

            },
            floatingActionButtonPosition = FabPosition.End,
        )
    }

}