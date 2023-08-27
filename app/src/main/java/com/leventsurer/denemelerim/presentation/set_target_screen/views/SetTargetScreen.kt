package com.leventsurer.denemelerim.presentation.set_target_screen.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel
import com.leventsurer.denemelerim.presentation.set_target_screen.views.composable.CustomSpinnerDialog
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor

@Composable
fun SetTargetScreen(
    setTargetViewModel: SetTargetViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
) {

    var targetUniversity by remember { mutableStateOf("") }
    var targetDepartment by remember { mutableStateOf("") }
    var isErrorVisible by remember { mutableStateOf(false) }
    val showDialog = remember {mutableStateOf(false) }
    val isLoading by remember { mutableStateOf(false) }
    var isRequestNeccessary by remember {mutableStateOf(true) }
    var isChoosingUniversity by remember {mutableStateOf(true) }
    val universitiesOrDepartmentArrayList = arrayListOf<String>()

    val universitiesAndDepartmentsState =
        setTargetViewModel.universitiesAndDepartmentsState.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.target_image),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(horizontal = 15.dp)
        )

        Text(
            text = "Giriş yapmadan önce hedefinizi öğrenmemiz gerekiyor.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(25.dp))

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            onClick = {
                isChoosingUniversity = true
                setTargetViewModel.onEvent(
                    SetTargetEvent.GetUniversitiesAndDepartment
                )
                isRequestNeccessary = true


            }) {
            if (targetUniversity.isEmpty()) {
                Text(text = "Hedef Üniversite")
            } else {
                Text(text = targetUniversity)

            }
        }

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            onClick = {
                isChoosingUniversity = false
                setTargetViewModel.onEvent(
                    SetTargetEvent.GetUniversitiesAndDepartment
                )
                isRequestNeccessary = true
            }) {
            if (targetDepartment.isEmpty()) {
                Text(text = "Hedef Bölüm")
            } else {
                Text(text = targetDepartment)

            }
        }


        if (universitiesAndDepartmentsState.isLoading) {
            CircularProgressIndicator()
        }

        if (universitiesAndDepartmentsState.result == true && isRequestNeccessary) {
            if (!universitiesAndDepartmentsState.universitiesAndDepartments.isNullOrEmpty()) {
                if (isChoosingUniversity) {
                    for (universities in universitiesAndDepartmentsState.universitiesAndDepartments) {
                        if (universities.universityName !in universitiesOrDepartmentArrayList) {
                            universitiesOrDepartmentArrayList.add(universities.universityName)
                        }
                    }
                } else {
                    for (departments in universitiesAndDepartmentsState.universitiesAndDepartments) {
                        if (departments.universityName == targetUniversity) {
                            universitiesOrDepartmentArrayList.add(departments.departmentName)
                        }
                    }
                }

                showDialog.value = true


            }
        }

        if (showDialog.value) {
            CustomSpinnerDialog(
                showDialog = showDialog.value,
                options = universitiesOrDepartmentArrayList,
                onDismiss = {
                    showDialog.value = false
                    isRequestNeccessary = false

                },
                onItemClick = { selectedItem ->
                    if (isChoosingUniversity) {
                        targetUniversity = selectedItem
                    } else {
                        targetDepartment = selectedItem
                    }
                    showDialog.value = false
                }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        ElevatedButton(
            onClick = {
                if (targetUniversity.isNotEmpty() && targetDepartment.isNotEmpty()) {
                    isErrorVisible = false

                    setTargetViewModel.onEvent(
                        SetTargetEvent.SetTarget(
                            targetUniversity,
                            targetDepartment,
                            dataStoreViewModel.getUserUidFromDataStore()
                        )
                    )


                } else {
                    isErrorVisible = true
                }
            }) {
            if (setTargetViewModel.setTargetState.value.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "Hedef Belirle")
            }

        }

        if (setTargetViewModel.setTargetState.value.result == true) {
            LaunchedEffect(Unit) {
                navigateToHomeScreen()
            }
        }

        if (isErrorVisible) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Lütfen belirli bir hedef belirle...", color = Color.Red)
        }


    }


}

