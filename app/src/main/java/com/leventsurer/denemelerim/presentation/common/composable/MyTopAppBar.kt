package com.leventsurer.denemelerim.presentation.common.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(appBarTitle: String, navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Primary),
        title =
        {
            Text(appBarTitle, color = Color.White)
        },
        actions = {

            IconButton(
                onClick = {
                    navController.navigate(Screen.GoalsScreen.route)
                }) {

                Icon(
                    painter = painterResource(id = R.drawable.outline_sticky_note_2_24),
                    contentDescription = "asdasd",
                    tint = Color.White
                )
            }


            IconButton(
                onClick = {
                    navController.navigate(Screen.StatisticsScreen.route)
                }) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_bar_chart_24),
                    contentDescription = "asdasd",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(Screen.ProfileScreen.route)
                }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "sdgdfg",
                    tint = Color.White
                )
            }
        },
        navigationIcon = {
            if (navController.currentDestination?.route == Screen.HomeScreen.route) {
                IconButton(onClick = {
                    navController.navigate(Screen.LeaderboardScreen.route)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_people_24),
                        contentDescription = "asdasd",
                        tint = Color.White
                    )
                }
            } else {
                IconButton(onClick = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) {
                            inclusive = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "",
                        tint = Color.White
                    )

                }
            }

        }
    )
}