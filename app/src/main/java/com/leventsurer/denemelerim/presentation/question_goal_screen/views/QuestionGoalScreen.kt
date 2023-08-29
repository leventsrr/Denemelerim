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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.leaderboard_screen.composable.LeaderBoardCardPointRow
import com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable.QuestionGoalCard
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun QuestionGoalScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { MyTopAppBar(appBarTitle = "Sıralamam", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn() {
                    items(6) { index ->
                        QuestionGoalCard()
                    }
                }
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Secondary,
                onClick = {  }
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