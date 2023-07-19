package com.leventsurer.denemelerim.presentation.add_exam_screen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable.LessonCorrectAndFalseInputs


@Composable
fun ScreenForTyt() {
    var turkishCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var turkishFalse by rememberSaveable {
        mutableStateOf("")
    }
    var socialCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var socialFalse by rememberSaveable {
        mutableStateOf("")
    }
    var mathCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var mathFalse by rememberSaveable {
        mutableStateOf("")
    }
    var scienceCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var scienceFalse by rememberSaveable {
        mutableStateOf("")
    }
    Column() {
        LessonCorrectAndFalseInputs("Türkçe", onCorrectValueChange = {turkishCorrect = it}, onFalseValueChange = {turkishFalse = it}, turkishCorrect , turkishFalse )

        LessonCorrectAndFalseInputs("Sosyal", onCorrectValueChange = {socialCorrect = it}, onFalseValueChange = {socialFalse = it}, socialCorrect , socialFalse )

        LessonCorrectAndFalseInputs("Matematik", onCorrectValueChange = {mathCorrect = it}, onFalseValueChange = {mathFalse = it}, mathCorrect , mathFalse )

        LessonCorrectAndFalseInputs("Fen", onCorrectValueChange = {scienceCorrect = it}, onFalseValueChange = {scienceFalse = it}, scienceCorrect , scienceFalse )
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Kaydet")
            }
    }
}

