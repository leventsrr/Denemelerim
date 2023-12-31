package com.leventsurer.denemelerim.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModelArgType
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.AddExamScreen
import com.leventsurer.denemelerim.presentation.exam_detail_screen.views.ExamDetailScreen
import com.leventsurer.denemelerim.presentation.home_screen.views.HomeScreen
import com.leventsurer.denemelerim.presentation.login_screen.views.LoginScreen
import com.leventsurer.denemelerim.presentation.profile_screen.views.ProfileScreen
import com.leventsurer.denemelerim.presentation.register_screen.views.RegisterScreen
import com.leventsurer.denemelerim.presentation.leaderboard_screen.views.LeaderboardScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.QuestionGoalScreen
import com.leventsurer.denemelerim.presentation.set_target_screen.views.SetTargetScreen
import com.leventsurer.denemelerim.presentation.splash_screen.views.SplashScreen
import com.leventsurer.denemelerim.presentation.statistics_screen.views.StatisticsScreen
import com.leventsurer.denemelerim.presentation.tracking_screen.study_goals.views.StudyGoalScreen
import com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.TytExamDetail
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.DenemelerimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DenemelerimTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.GoalsScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(
                                navigateToHomeScreen = { navController.navigate(Screen.HomeScreen.route) },
                                navigateToSignUp = { navController.navigate(Screen.RegisterScreen.route) }
                            )
                        }

                        composable(route = Screen.RegisterScreen.route) {
                            RegisterScreen(
                                navigateToSetTargetScreen = { navController.navigate(Screen.SetTargetScreen.route) },
                                navController = navController,
                            )
                        }

                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }

                        composable(route = Screen.AddExamScreen.route) {
                            AddExamScreen(navController = navController)
                        }

                        composable(Screen.StatisticsScreen.route) {
                            StatisticsScreen(navController = navController)
                        }

                        composable(Screen.ProfileScreen.route) {
                            ProfileScreen(navController = navController)
                        }

                        composable(Screen.LeaderboardScreen.route) {
                            LeaderboardScreen(navController = navController)
                        }

                        composable(Screen.ExamDetailScreen.route) {
                            ExamDetailScreen(navController = navController)
                        }

                        composable(Screen.SetTargetScreen.route) {
                            SetTargetScreen(
                                navigateToHomeScreen = { navController.navigate(Screen.HomeScreen.route) },
                            )
                        }

                        composable(Screen.GoalsScreen.route) {
                            QuestionGoalScreen(
                                navController = navController
                            )
                        }

                        composable(Screen.SplashScreen.route) {
                            SplashScreen(
                                navigateToHomeScreen = { navController.navigate(Screen.HomeScreen.route) },
                                navigateToLoginScreen = { navController.navigate(Screen.LoginScreen.route) }
                            )
                        }

                        composable(
                            route = "${Screen.TytExamDetail.route}/{tytExamDetail}",
                            arguments = listOf(navArgument("tytExamDetail"){
                                type = NewTytExamModelArgType()
                            }
                            )
                        ) {navBackStackEntry->
                            val tytExamDetail = navBackStackEntry.arguments?.getString("tytExamDetail")?.let { Gson().fromJson(it,NewTytExamModel::class.java) }
                            TytExamDetail(tytExamDetail!!, navController)
                        }



                    }
                }
            }
        }
    }
}
