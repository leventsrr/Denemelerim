package com.leventsurer.denemelerim.presentation.profile_screen.composable

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.authentication.AuthenticationViewModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.common.database.DatabaseViewModel
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@Composable
fun Buttons(
    setTargetViewModel: SetTargetViewModel,
    newTargetUniversity: String,
    newTargetDepartment: String,
    dataStoreViewModel: DataStoreViewModel,
    changeTargetState: SetTargetState,
    authenticationViewModel:AuthenticationViewModel,
    navController:NavController

    ) {
    val databaseViewModel:DatabaseViewModel = hiltViewModel()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onClick = {

                setTargetViewModel.onEvent(
                    SetTargetEvent.SetTarget(
                        newTargetUniversity,
                        newTargetDepartment,
                        dataStoreViewModel.getUserUidFromDataStore()
                    )
                )
            }) {
            if (changeTargetState.isLoading) {
                CircularProgressIndicator()
            } else if (changeTargetState.result == true) {
                Text(text = "Değişiklikleri Kaydet")
            } else {
                Text(text = "Değişiklikleri Kaydet")
            }

        }
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            onClick = {
                authenticationViewModel.logout()
                dataStoreViewModel.clearAllDataStore()
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }) {
            Text(text = "Hesaptan Çık")
        }

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {
                databaseViewModel.onEvent(DatabaseEvent.DeleteUserAccount(
                    dataStoreViewModel.getUserUidFromDataStore()
                ))
                authenticationViewModel.deleteAccount()
                dataStoreViewModel.clearAllDataStore()
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }) {
            Text(text = "Hesabı Sil")
        }
    }

}