package com.leventsurer.denemelerim.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.AddExamScreen
import com.leventsurer.denemelerim.presentation.home_screen.views.HomeScreen
import com.leventsurer.denemelerim.presentation.login_screen.views.LoginScreen
import com.leventsurer.denemelerim.presentation.register_screen.views.RegisterScreen
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.DenemelerimTheme

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
                    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){
                        composable(route = Screen.LoginScreen.route){
                            LoginScreen(navController = navController)
                        }

                        composable(route = Screen.RegisterScreen.route){
                            RegisterScreen(navController = navController)
                        }

                        composable(route = Screen.HomeScreen.route){
                            HomeScreen(navController = navController)
                        }

                        composable(route = Screen.AddExamScreen.route){
                            AddExamScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
