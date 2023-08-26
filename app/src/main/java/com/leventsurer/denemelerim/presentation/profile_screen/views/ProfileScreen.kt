package com.leventsurer.denemelerim.presentation.profile_screen.views

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.authentication.AuthenticationViewModel
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.login_screen.LoginViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.composable.Buttons
import com.leventsurer.denemelerim.presentation.profile_screen.composable.ChangeTargetCard
import com.leventsurer.denemelerim.presentation.profile_screen.composable.ProfileInformationCard
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel
import com.leventsurer.denemelerim.presentation.set_target_screen.views.composable.CustomSpinnerDialog
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor

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
                    .padding(padding)
                    .background(Color.LightGray),
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