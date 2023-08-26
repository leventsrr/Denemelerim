package com.leventsurer.denemelerim.presentation.set_target_screen.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel

@Composable
fun SetTargetScreen(
    setTargetViewModel: SetTargetViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
) {

    var targetUniversity by remember { mutableStateOf("") }
    var targetFaculty by remember { mutableStateOf("") }
    var isErrorVisible by remember {
        mutableStateOf(false)
    }

    val universities = remember {
        mutableStateListOf<String>()
    }
    val departments = remember {
        mutableStateListOf<String>()
    }
    var universitiesArrayList= arrayListOf<String>()
    var departmentsArrayList = arrayListOf<String>()



    setTargetViewModel.onEvent(SetTargetEvent.GetUniversitiesAndDepartment)



    val setTargetViewModelState = setTargetViewModel.universitiesAndDepartmentsState.value
    if(setTargetViewModelState.universitiesAndDepartments!=null){
        for (universitiesAndDepartments in setTargetViewModelState.universitiesAndDepartments){
            universities.add(universitiesAndDepartments.universityName)
            departments.add(universitiesAndDepartments.departmentName)
        }
        universitiesArrayList = ArrayList(universities)
        departmentsArrayList = ArrayList(departments)

    }






    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Giriş yapmadan önce hedefinizi öğrenmemiz gerekiyor.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(5.dp))
        if(setTargetViewModelState.isLoading){
            CircularProgressIndicator()
        }else {
            CustomSpinner(
                spinnerTitle = "Hedef Üniversite",
                listOfOptions = universitiesArrayList,
                onClick = {
                    targetUniversity = it
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            CustomSpinner(
                spinnerTitle = "Hedef Bölüm",
                listOfOptions = departmentsArrayList,
                onClick = { targetFaculty = it })
        }

        Spacer(modifier = Modifier.height(5.dp))


        ElevatedButton(
            onClick = {
                if (targetUniversity.isNotEmpty() && targetFaculty.isNotEmpty()) {
                    isErrorVisible = false

                    setTargetViewModel.onEvent(
                        SetTargetEvent.SetTarget(
                            targetUniversity,
                            targetFaculty,
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