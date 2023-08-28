package com.leventsurer.denemelerim.presentation.leaderboard_screen.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.leaderboard_screen.LeaderboardEvent
import com.leventsurer.denemelerim.presentation.leaderboard_screen.LeaderboardViewModel
import com.leventsurer.denemelerim.presentation.leaderboard_screen.composable.RankingCard
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.ui.theme.Fourth
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeaderboardScreen(
    navController: NavController,
    leaderboardViewModel: LeaderboardViewModel = hiltViewModel(),
    datastoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val leaderboardState = leaderboardViewModel.leaderboardState.value
    val users = arrayListOf<UserModel>()
    val profileState = profileViewModel.getUserProfileInfoState.value
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))

    LaunchedEffect(Unit) {
        profileViewModel.onEvent(
            ProfileEvent.GetUserProfileInfo(
                datastoreViewModel.getUserUidFromDataStore()
            )
        )
    }


    Scaffold(
        //containerColor = Color.LightGray,
        topBar = { MyTopAppBar(appBarTitle = "Sıralamam", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (profileState.isLoading) {
                    LottieAnimation(
                        modifier = Modifier.fillMaxWidth(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                } else if (profileState.result != null) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = profileState.result.targetUniversity,
                            fontWeight = FontWeight.ExtraBold,
                            color = Primary,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = profileState.result.targetDepartment,
                            fontWeight = FontWeight.Bold,
                            color = Fourth,
                            fontSize = 25.sp
                        )
                    }


                }
                if(profileState.result!=null){
                        LaunchedEffect(Unit ){
                            leaderboardViewModel.onEvent(
                                LeaderboardEvent.GetUsersToLeaderboard(
                                    profileState.result.targetUniversity,
                                    profileState.result.targetDepartment
                                )
                            )
                        }

                }
                if(leaderboardState.isLoading){
                    CircularProgressIndicator()
                }else if(leaderboardState.users !=null){
                    users.addAll(leaderboardState.users)
                    LazyColumn() {
                        items(users.size) { index ->
                            RankingCard(rank = index + 1, userModel = users[index])
                        }
                    }
                }

            }
        }

    )
}

