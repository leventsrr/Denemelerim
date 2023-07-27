package com.leventsurer.denemelerim.presentation.register_screen.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.MainActivity
import com.leventsurer.denemelerim.presentation.register_screen.RegisterEvent
import com.leventsurer.denemelerim.presentation.register_screen.RegisterState
import com.leventsurer.denemelerim.presentation.register_screen.RegisterViewModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor


@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    var isPasswordsDifferent by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }

    val state = registerViewModel.state.value
    if (state.user != null) {
        LaunchedEffect(Unit) {
            navController.navigate(Screen.HomeScreen.route)
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier

            .fillMaxSize()
            .background(PrimaryColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
        )

        Card(
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxHeight()
                .weight(8f)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {

                Text(
                    text = "Kayıt Ol",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Email") },
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
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Kullanıcı Adı") },
                    value = userName,
                    onValueChange = {
                        userName = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Password"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Şifre") },
                    isError = isPasswordsDifferent,
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Şfre Tekrar") },
                    isError = isPasswordsDifferent,
                    value = passwordAgain,
                    onValueChange = {
                        passwordAgain = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },

                    )
                if (isPasswordsDifferent) {
                    Text(text = "Şifreler uyumlu değil.", color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {
                        if (password == passwordAgain) {


                            registerViewModel.onEvent(
                                RegisterEvent.SignUp(
                                    userName,
                                    email,
                                    password
                                )
                            )

                        } else {
                            isPasswordsDifferent = true
                        }
                    })
                {
                    if (state.isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Text(text = "Kayıt Ol")
                    }

                }
            }
        }

        Text(text = "Giriş Yap",
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .padding(vertical = 10.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold)
    }
}