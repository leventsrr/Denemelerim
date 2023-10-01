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
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.view.composable.AddNewQuestionGoalBottomSheet
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.tracking_screen.lesson_topic_tracking.view.LessonsTopicScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.view.QuestionGoalsScreen

@Composable
fun QuestionGoalScreen(
    navController: NavController
) {


    var showSheet by remember { mutableStateOf(false) }


    var state by remember { mutableStateOf(0) }
    val tabTitles = arrayListOf("Konu Takibim","Soru Hedeflerim")
    if (showSheet) {
        AddNewQuestionGoalBottomSheet() {
            showSheet = false
        }
    }
    var isInQuestionGoalScreen by remember {
        mutableStateOf(false)
    }


    
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
                if(state == 0){
                    isInQuestionGoalScreen = false
                    LessonsTopicScreen()
                }
                else if(state == 1){
                    isInQuestionGoalScreen = true
                    QuestionGoalsScreen()

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
            }

        },
        floatingActionButtonPosition = FabPosition.End,
    )
}