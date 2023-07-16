package com.leventsurer.denemelerim.presentation.login_screen.views

import android.content.res.Resources.Theme
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.BackgroundBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController:NavController
) {
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier

            .fillMaxSize()
            .background(BackgroundBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.fillMaxWidth()
        )
        
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    ) {
                
                Text(text = "Giriş Yap", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(

                    value = email,
                    onValueChange = {
                                    email = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                                    password = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Email"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Text(text = "Giriş Yap")
                }
            }
        }
        
        Text(text = "Kayıt Ol", modifier = Modifier
            .clickable {
                navController.navigate(Screen.RegisterScreen.route)
            }
            .padding(vertical = 15.dp), color = Color.White, fontWeight = FontWeight.Bold)
    }
}