package com.leventsurer.denemelerim.presentation.register_screen.views

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.common.database.DatabaseEvent
import com.leventsurer.denemelerim.presentation.common.database.DatabaseViewModel
import com.leventsurer.denemelerim.presentation.register_screen.RegisterEvent
import com.leventsurer.denemelerim.presentation.register_screen.RegisterViewModel
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.util.Constants.USER_UID


@Composable
fun RegisterScreen(
    navigateToSetTargetScreen: () -> Unit,
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    databaseViewModel: DatabaseViewModel = hiltViewModel(),
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }


    val state = registerViewModel.state.value




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
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp)
            ) {

                Text(
                    text = "Kayıt Ol",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedTextField(modifier = Modifier.weight(1f),
                    label = { Text(text = "Email") },
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email, contentDescription = "Email"
                        )
                    })
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(modifier = Modifier.weight(1f),
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
                    })
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(modifier = Modifier.weight(1f),
                    label = { Text(text = "Şifre") },
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock, contentDescription = "Password"
                        )
                    })
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    label = { Text(text = "Şfre Tekrar") },
                    value = passwordAgain,
                    onValueChange = {
                        passwordAgain = it
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock, contentDescription = "Password"
                        )
                    },

                    )

                if (state.error != null) {
                    Text(text = state.error, color = MaterialTheme.colorScheme.error)
                }

                if (state.user != null) {
                    databaseViewModel.onEvent(
                        DatabaseEvent.AddNewUser(
                            state.user.uid
                        )
                    )
                    dataStoreViewModel.putUserUidToDataStore(USER_UID, state.user.uid)
                    LaunchedEffect(Unit) {
                        navigateToSetTargetScreen()
                    }


                }

                Spacer(modifier = Modifier.height(5.dp))
                Button(onClick = {
                    registerViewModel.onEvent(
                        RegisterEvent.SignUp(
                            userName, email, password, passwordAgain
                        )
                    )
                }) {
                    if (state.isLoading || databaseViewModel.addNewUserState.value.isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Text(text = "Kayıt Ol")
                    }

                }
            }
        }

        Text(text = "Giriş Yap", modifier = Modifier
            .clickable {
                navController.popBackStack()
            }
            .padding(vertical = 10.dp), color = Color.White, fontWeight = FontWeight.Bold)
    }


}