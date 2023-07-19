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
fun ScreenForAyt() {
    var mathCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var mathFalse by rememberSaveable {
        mutableStateOf("")
    }
    var sciencecorrect by rememberSaveable {
        mutableStateOf("")
    }
    var scienceFalse by rememberSaveable {
        mutableStateOf("")
    }
    var socialCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var socialFalse by rememberSaveable {
        mutableStateOf("")
    }
    var turkishLanguageAndLiteratureCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var turkishLanguageAndLiteratureFalse by rememberSaveable() {
        mutableStateOf("")
    }


    Column() {
        LessonCorrectAndFalseInputs(
            lessonTitle = "Matematik",
            onCorrectValueChange = {mathCorrect = it},
            onFalseValueChange = {mathFalse = it},
            correctValue = mathCorrect,
            falseValue = mathFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Sosyal-2",
            onCorrectValueChange = {socialCorrect = it},
            onFalseValueChange = {socialFalse = it},
            correctValue = socialCorrect,
            falseValue = socialFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Fen",
            onCorrectValueChange = {sciencecorrect = it},
            onFalseValueChange = {scienceFalse = it},
            correctValue = sciencecorrect,
            falseValue = scienceFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Türk Dili ve Edebiyatı",
            onCorrectValueChange = {turkishLanguageAndLiteratureCorrect = it},
            onFalseValueChange = {turkishLanguageAndLiteratureFalse = it},
            correctValue = turkishLanguageAndLiteratureCorrect,
            falseValue = turkishLanguageAndLiteratureFalse
        )
        
        ElevatedButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 5.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Kaydet")
        }
    }
}