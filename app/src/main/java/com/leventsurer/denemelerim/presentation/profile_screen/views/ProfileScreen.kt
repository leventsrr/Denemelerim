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
    var newTargetUniversity by remember{ mutableStateOf("Hedef Üniversite") }
    var newTargetDepartment by remember{ mutableStateOf("Hedef Bölüm") }
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
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))



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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                ) {

                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    ) {
                        Column(Modifier.padding(10.dp)) {
                            Text(
                                text = "Profil Bilgileri",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp),
                                textAlign = TextAlign.Center
                            )
                            Divider()
                            if (state.result == null) {
                               Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                                   LottieAnimation(
                                       modifier = Modifier.width(100.dp).height(100.dp),
                                       composition = composition,
                                       iterations = LottieConstants.IterateForever,
                                   )
                               }
                            } else {
                                Text(text = "Kullanıcı Adı:${state.result.userName}")
                                Text(text = "Çözülen TYT Deneme Sayısı: ${state.result.numberOfTytExam}")
                                Text(text = "Çözülen AYT Deneme Sayısı: ${state.result.numberOfAytExam}")
                                Text(text = "Ortalama TYT Puanı: ${state.result.averageTytPoint}")
                                Text(text = "Ortalama Sayısal AYT Puanı: ${state.result.averageNumericalPoint}")
                                Text(text = "Ortalama E.A AYT Puanı: ${state.result.averageEqualWeightPoint}")
                                Text(text = "Ortalama Sözel AYT Puanı: ${state.result.averageVerbalPoint}")
                                Text(text = "Ortalama YKS Puanı: ${state.result.averageYksPoint}")
                                Text(text = "Hedef Üniversite: ${state.result.targetUniversity}")
                                Text(text = "Hedef Bölüm: ${state.result.targetDepartment}")
                            }
                        }
                    }
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
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                            onClick = {
                                isChoosingUniversity = true
                                setTargetViewModel.onEvent(
                                    SetTargetEvent.GetUniversitiesAndDepartment
                                )
                                isRequestNeccessary = true


                            }) {
                                if(state.result !=null){
                                    Text(text = newTargetUniversity?:"")
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
                            if(state.result !=null){
                                Text(text = newTargetDepartment?:"")

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
                            Log.e("kontrol", "onDismiss")
                            showDialog.value = false
                            isRequestNeccessary = false

                        },
                        onItemClick = { selectedItem ->
                            if(isChoosingUniversity){
                                newTargetUniversity = selectedItem
                            }else{
                                newTargetDepartment = selectedItem
                            }
                            showDialog.value = false
                        }
                    )
                }
                if(universitiesAndDepartmentsState.isLoading){
                    CircularProgressIndicator()
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ElevatedButton(
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
                        if(changeTargetState.isLoading){
                            CircularProgressIndicator()
                        }else if (changeTargetState.result == true){
                            Toast.makeText(LocalContext.current,"Hedefiniz Güncellendi.Sonraki gelişinizde veriler güncellenecek.",Toast.LENGTH_LONG).show()
                            Text(text = "Değişiklikleri Kaydet")
                        }
                        else{
                            Text(text = "Değişiklikleri Kaydet")
                        }

                    }
                    ElevatedButton(
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
                        onClick = { /*TODO*/ }) {
                        Text(text = "Hesabı Sil")
                    }
                }
            }
        })
}