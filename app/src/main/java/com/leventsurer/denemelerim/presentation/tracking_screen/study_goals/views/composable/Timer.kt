package com.leventsurer.denemelerim.presentation.tracking_screen.study_goals.views.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.common.composable.CustomSpinner
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun Timer() {

    var chosenStudyType by remember {
        mutableStateOf("")
    }

    var isTimerBegin by remember {
        mutableStateOf(false)
    }

    var studyTime by remember {
        mutableStateOf("")
    }

    var isError by remember{
        mutableStateOf(false)
    }

    var errorText by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        if (isTimerBegin) {
            CircularProgressIndicator(
                progress = 0.5f,
                color = Primary,
                strokeWidth = 25.dp,
                trackColor = Secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
        } else {
            CustomSpinner(
                spinnerTitle = "Çalışma Türü",
                listOfOptions = arrayListOf(
                    "Sınav Analizi",
                    "Deneme Çözme",
                    "Tekrar",
                    "Soru Çözümü"
                ),
                onClick = {
                    chosenStudyType = it
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                isError = isError,

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    unfocusedPlaceholderColor = Color.Black
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = studyTime,
                onValueChange = {
                    studyTime = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = "Çalışma Zamanı (Dakika)") }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                IconButton(
                    onClick = {

                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_cancel_24),
                        contentDescription = "asdasd",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                }

                IconButton(
                    onClick = {
                        if(studyTime.isEmpty()){
                            isError = true
                            errorText = "Çalışma zamanı doldurulmalıdır"
                        } else if(chosenStudyType.isEmpty()){
                            isError = true
                            errorText = "Çalışma türünü belirleyin"
                        }else{
                            isTimerBegin = true
                        }


                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_play_circle_filled_24),
                        contentDescription = "asdasd",
                        tint = Secondary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            if(isError){
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = errorText, color = Color.Red, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }


    }
}