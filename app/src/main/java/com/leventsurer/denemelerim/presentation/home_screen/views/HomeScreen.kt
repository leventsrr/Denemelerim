package com.leventsurer.denemelerim.presentation.home_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.ExamCard
import com.leventsurer.denemelerim.presentation.common.MyTopAppBar
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.ui.theme.Third
import com.leventsurer.denemelerim.presentation.ui.theme.fourthColor
import com.leventsurer.denemelerim.presentation.ui.theme.secondaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.LightGray,
        bottomBar = {},
        topBar = {
            MyTopAppBar("Denemelerim", navController = navController)
        },
        floatingActionButton = {

            FloatingActionButton(
                containerColor = PrimaryColor,
                onClick = { navController.navigate(Screen.AddExamScreen.route)}
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Exam")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {padding->
            LazyColumn(modifier = Modifier.fillMaxHeight().padding(padding)){
                items(15){
                   ExamCard(navController = navController)
                }
            }
        }
    )
}





