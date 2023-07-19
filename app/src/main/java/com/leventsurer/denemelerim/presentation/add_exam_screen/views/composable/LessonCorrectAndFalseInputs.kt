package com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Source about moving state -> https://developer.android.com/jetpack/compose/state
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonCorrectAndFalseInputs(lessonTitle:String, onCorrectValueChange:(String)->Unit, onFalseValueChange:(String)->Unit, correctValue:String, falseValue:String) {
    Column() {
        Text(text = lessonTitle, modifier = Modifier.padding(start = 10.dp))

        Column() {

            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = correctValue,
                onValueChange = onCorrectValueChange,
                label = { Text(text = "$lessonTitle Doğru Sayısı" ) },
                textStyle = TextStyle.Default.copy(fontSize = 15.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = falseValue,
                onValueChange = onFalseValueChange,
                label = { Text(text = "$lessonTitle Yanlış Sayısı") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

}
