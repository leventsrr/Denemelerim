package com.leventsurer.denemelerim.presentation.leaderboard_screen





sealed class LeaderboardEvent{

    data class GetUsersToLeaderboard(val universityName:String,val departmentName:String):LeaderboardEvent()

}
