package com.leventsurer.denemelerim.presentation.profile_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.leventsurer.denemelerim.presentation.common.authentication.AuthenticationViewModel
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.composable.MyTopAppBar
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.login_screen.LoginViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.ui.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(

    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    authenticationViewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = profileViewModel.getUserProfileInfoState.value
    profileViewModel.onEvent(
        ProfileEvent.GetUserProfileInfo(dataStoreViewModel.getUserUidFromDataStore())
    )

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
                                CircularProgressIndicator()
                            } else {
                                Text(text = "Kullanıcı Adı:${state.result.userName}")
                                Text(text = "Çözülen TYT Deneme Sayısı: ${state.result.numberOfTytExam}")
                                Text(text = "Çözülen AYT Deneme Sayısı: ${state.result.numberOfAytExam}")
                                Text(text = "Ortalama TYT Puanı: ${state.result.averageTytPoint}")
                                Text(text = "Ortalama AYT Puanı: ${state.result.averageAytPoint}")
                                Text(text = "Ortalama YKS Puanı: ${state.result.averageYksPoint}")
                                Text(text = "Hedef Üniversite: ${state.result.targetUniversity}")
                                Text(text = "Hedef Bölüm: ${state.result.targetDepartment}")
                            }

                        }
                    }
                }

                ElevatedButton(onClick = {

                }) {
                    Text(text = "aaa")
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
                        CustomSpinner(
                            spinnerTitle = "Hedef Üniversite",
                            listOfOptions = arrayListOf("Gazi", "ODTU", "itü"),
                            onClick = {})
                        CustomSpinner(
                            spinnerTitle = "Hedef Bölüm",
                            listOfOptions = arrayListOf("Gazi", "ODTU", "itü"),
                            onClick = {})
                    }
                }


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ElevatedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        onClick = { /*TODO*/ }) {
                        Text(text = "Değişiklikleri Kaydet")
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