package com.leventsurer.denemelerim.presentation.profile_screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetEvent
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetState
import com.leventsurer.denemelerim.presentation.set_target_screen.SetTargetViewModel
import com.leventsurer.denemelerim.presentation.set_target_screen.views.composable.CustomSpinnerDialog
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun ChangeTargetCard(
    setTargetViewModel: SetTargetViewModel,
    state: ProfileState,
    universitiesAndDepartmentsState: SetTargetState,
    setTargetUniversity: (String) -> Unit,
    setTargetDepartment: (String) -> Unit,
) {

    var newTargetUniversity by remember { mutableStateOf("Hedef Üniversite") }
    var newTargetDepartment by remember { mutableStateOf("Hedef Bölüm") }
    var isRequestNeccessary by remember {
        mutableStateOf(true)
    }
    var isChoosingUniversity by remember {
        mutableStateOf(true)
    }
    val universitiesOrDepartmentArrayList = arrayListOf<String>()
    val showDialog = remember {
        mutableStateOf(false)
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Hedef Değiştir",
                color = Secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center
            )
            Divider()
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                onClick = {
                    isChoosingUniversity = true
                    setTargetViewModel.onEvent(
                        SetTargetEvent.GetUniversitiesAndDepartment
                    )
                    isRequestNeccessary = true

                }) {
                if (state.result != null) {
                    Text(text = newTargetUniversity)

                }


            }

            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                onClick = {
                    isChoosingUniversity = false
                    setTargetViewModel.onEvent(
                        SetTargetEvent.GetUniversitiesAndDepartment
                    )
                    isRequestNeccessary = true

                }) {
                if (state.result != null) {
                    Text(text = newTargetDepartment)
                }
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
                            if (departments.universityName == newTargetUniversity) {
                                universitiesOrDepartmentArrayList.add(departments.departmentName)
                            }
                        }
                    }
                    showDialog.value = true
                }
            }
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
                    newTargetUniversity = selectedItem
                    setTargetUniversity(selectedItem)
                } else {
                    newTargetDepartment = selectedItem
                    setTargetDepartment(selectedItem)
                }
                showDialog.value = false
            }
        )
    }

    if (universitiesAndDepartmentsState.isLoading) {
        CircularProgressIndicator()
    }

}