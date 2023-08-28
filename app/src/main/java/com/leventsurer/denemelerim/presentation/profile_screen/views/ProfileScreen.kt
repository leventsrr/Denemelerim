package com.leventsurer.denemelerim.presentation.profile_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.authentication.AuthenticationViewModel
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.composable.Buttons
import com.leventsurer.denemelerim.presentation.profile_screen.composable.ChangeTargetCard
import com.leventsurer.denemelerim.presentation.profile_screen.composable.ProfileInformationCard
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(

    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    setTargetViewModel: SetTargetViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = profileViewModel.getUserProfileInfoState.value
    val changeTargetState = setTargetViewModel.setTargetState.value
    val universitiesAndDepartmentsState = setTargetViewModel.universitiesAndDepartmentsState.value
    var newTargetUniversity by remember { mutableStateOf("Hedef Üniversite") }
    var newTargetDepartment by remember { mutableStateOf("Hedef Bölüm") }





    LaunchedEffect(Unit) {
        profileViewModel.onEvent(
            ProfileEvent.GetUserProfileInfo(dataStoreViewModel.getUserUidFromDataStore())
        )
    }



    Scaffold(
        topBar = { MyTopAppBar("Profilim", navController = navController) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                ProfileInformationCard(state = state)

                ChangeTargetCard(
                    setTargetViewModel = setTargetViewModel,
                    state = state,
                    universitiesAndDepartmentsState = universitiesAndDepartmentsState,
                    setTargetDepartment = { newTargetDepartment = it },
                    setTargetUniversity = { newTargetUniversity = it }
                )

                Buttons(
                    setTargetViewModel = setTargetViewModel,
                    newTargetUniversity = newTargetUniversity,
                    newTargetDepartment = newTargetDepartment,
                    dataStoreViewModel = dataStoreViewModel,
                    changeTargetState = changeTargetState,
                    authenticationViewModel = authenticationViewModel,
                    navController = navController
                )
            }
        })
}