package com.leventsurer.denemelerim.presentation.splash_screen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun SplashScreen(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,

    ) {
    if (dataStoreViewModel.getIsFirstLoginInfo() == true) {
        LaunchedEffect(Unit) {
            navigateToHomeScreen()
        }
    } else {
        LaunchedEffect(Unit) {
            navigateToLoginScreen()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",

            )

        CircularProgressIndicator(trackColor = Secondary, modifier = Modifier.height(10.dp))
    }
}