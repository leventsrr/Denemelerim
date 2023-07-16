package com.leventsurer.denemelerim.presentation.ui

sealed class Screen (val route:String){
    object LoginScreen: Screen("login_screen")
    object RegisterScreen:Screen("register_screen")
    object HomeScreen:Screen("home_screen")
}