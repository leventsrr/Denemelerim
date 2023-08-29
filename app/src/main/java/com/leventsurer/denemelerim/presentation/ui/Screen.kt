package com.leventsurer.denemelerim.presentation.ui

sealed class Screen (val route:String){
    object LoginScreen: Screen("login_screen")
    object RegisterScreen:Screen("register_screen")
    object HomeScreen:Screen("home_screen")
    object AddExamScreen:Screen("add_exam_screen")
    object StatisticsScreen:Screen("statistics_screen")
    object ProfileScreen:Screen("profile_screen")

    object LeaderboardScreen:Screen("leaderboard_screen")

    object ExamDetailScreen:Screen("exam_detail_screen")

    object SetTargetScreen:Screen("set_target_screen")

    object QuestionGoalScreen:Screen("question_goal_screen")
}