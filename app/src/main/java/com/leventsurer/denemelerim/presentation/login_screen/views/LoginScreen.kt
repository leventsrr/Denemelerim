package com.leventsurer.denemelerim.presentation.login_screen.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.login_screen.LoginEvent
import com.leventsurer.denemelerim.presentation.login_screen.LoginViewModel
import com.leventsurer.denemelerim.presentation.register_screen.RegisterEvent
import com.leventsurer.denemelerim.presentation.register_screen.RegisterViewModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor


@Composable
fun LoginScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToSignUp: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state = loginViewModel.state.value

    if(dataStoreViewModel.getIsFirstLoginInfo() == true){
        Log.e("kontrol","isFirstLogin true")
        LaunchedEffect(Unit) {
            navigateToHomeScreen()
        }
    }

    if (state.user != null) {
        dataStoreViewModel.putUserUidToDataStore(state.user.uid)
        dataStoreViewModel.putIsFirstLoginInfo()
        LaunchedEffect(Unit) {
            navigateToHomeScreen()
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
                .weight(3f)
        )

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(15.dp),

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {

                Text(
                    text = "Giriş Yap",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text("Email") },
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
                    label = { Text("Şifre") },
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
                        loginViewModel.onEvent(
                            LoginEvent.SignIn(
                                email,
                                password
                            )
                        )


                    }) {
                    if (state.isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else if (state.error != "") {
                        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
                    } else {
                        Text(text = "Giriş Yap")
                    }

                }
            }
        }

        Text(text = "Kayıt Ol", modifier = Modifier
            .clickable {
                navigateToSignUp()
            }
            .padding(vertical = 10.dp), color = Color.White, fontWeight = FontWeight.Bold)
    }
}