package com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Source about moving state -> https://developer.android.com/jetpack/compose/state

@Composable
fun LessonCorrectAndFalseInputs(lessonTitle:String, onCorrectValueChange:(String)->Unit, onFalseValueChange:(String)->Unit, correctValue:String, falseValue:String) {

    Column() {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = lessonTitle, modifier = Modifier.padding(start = 10.dp))

        Row() {

            OutlinedTextField(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
                value = correctValue,
                onValueChange = onCorrectValueChange,
                label = {Text(text = "Doğru Sayısı" )},
                textStyle = TextStyle.Default.copy(fontSize = 15.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            
            Spacer(modifier = Modifier.width(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .weight(1f),
                value = falseValue,
                onValueChange = onFalseValueChange,
                label = { Text(text = "Yanlış Sayısı") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        }
    }

}
