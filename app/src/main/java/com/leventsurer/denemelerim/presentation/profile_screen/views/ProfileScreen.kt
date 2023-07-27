package com.leventsurer.denemelerim.presentation.profile_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.leventsurer.denemelerim.presentation.common.CustomSpinner
import com.leventsurer.denemelerim.presentation.common.MyTopAppBar
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
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
                Image(
                    painter = rememberImagePainter(data = "https://picsum.photos/200/300"),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)


                )

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text(text = "Levent Sürer")
                        Text(text = "Çözülen Deneme Sayısı: 26")
                        Text(text = "Hedef: Gazi Üniversitesi Bilgisayar Mühendisliği")
                    }
                }
                Column() {
                    CustomSpinner(
                        spinnerTitle = "Hedef Üniversite",
                        listOfOptions = arrayListOf("Gazi", "ODTU", "itü"),
                        onClick = {})
                    CustomSpinner(
                        spinnerTitle = "Hedef Bölüm",
                        listOfOptions = arrayListOf("Gazi", "ODTU", "itü"),
                        onClick = {})
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
                        onClick = { /*TODO*/ }) {
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