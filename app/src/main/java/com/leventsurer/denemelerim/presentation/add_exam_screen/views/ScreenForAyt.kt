package com.leventsurer.denemelerim.presentation.add_exam_screen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
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
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor




@Composable
fun ScreenForAyt(examName:String,aboutExam:String) {
    var mathCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var mathFalse by rememberSaveable {
        mutableStateOf("")
    }
    //fen -> fizik-14,kimya-13 biyoloji-13
    var physicsCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var physicsFalse by rememberSaveable {
        mutableStateOf("")
    }
    var chemistryCorrect by rememberSaveable {
        mutableStateOf("")
    }
    var chemistryFalse by rememberSaveable {
        mutableStateOf("")
    }
    var biologyCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var biologyFalse by rememberSaveable() {
        mutableStateOf("")
    }
    //türk dil edebiyatı-> edebiyat-24 tarih-10 coğrafya-6
    var literatureCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var literatureFalse by rememberSaveable() {
        mutableStateOf("")
    }
    var historyCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var historyFalse by rememberSaveable() {
        mutableStateOf("")
    }
    var geographyCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var geographyFalse by rememberSaveable() {
        mutableStateOf("")
    }
    //sosyal bilimler ->tarih-11 coğrafya-11 felsefe-12 din-6
    var historyCorrectForSocial by rememberSaveable() {
        mutableStateOf("")
    }
    var historyFalseForSocial by rememberSaveable() {
        mutableStateOf("")
    }
    var geographyCorrectForSocial by rememberSaveable() {
        mutableStateOf("")
    }
    var geographyFalseForSocial by rememberSaveable() {
        mutableStateOf("")
    }
    var philosophyCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var philosophyFalse by rememberSaveable() {
        mutableStateOf("")
    }
    var religionCorrect by rememberSaveable() {
        mutableStateOf("")
    }
    var religionFalse by rememberSaveable() {
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
            lessonTitle = "Fizik",
            onCorrectValueChange = {physicsCorrect = it},
            onFalseValueChange = {physicsFalse = it},
            correctValue = physicsCorrect,
            falseValue = physicsFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Kimya",
            onCorrectValueChange = {chemistryCorrect = it},
            onFalseValueChange = {chemistryFalse = it},
            correctValue = chemistryCorrect,
            falseValue = chemistryFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Biyoloji",
            onCorrectValueChange = {biologyCorrect = it},
            onFalseValueChange = {biologyFalse = it},
            correctValue = biologyCorrect,
            falseValue = biologyFalse
        )
        //--------------------------------
        LessonCorrectAndFalseInputs(
            lessonTitle = "Edebiyat",
            onCorrectValueChange = {literatureCorrect = it},
            onFalseValueChange = {literatureFalse = it},
            correctValue = literatureCorrect,
            falseValue = literatureFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Tarih-1",
            onCorrectValueChange = {historyCorrect = it},
            onFalseValueChange = {historyFalse = it},
            correctValue = historyCorrect,
            falseValue = historyFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Coğrafya-1",
            onCorrectValueChange = {geographyCorrect = it},
            onFalseValueChange = {geographyFalse = it},
            correctValue = geographyCorrect,
            falseValue = geographyFalse
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Tarih-2",
            onCorrectValueChange = {historyCorrectForSocial = it},
            onFalseValueChange = {historyFalseForSocial = it},
            correctValue = historyCorrectForSocial,
            falseValue = historyFalseForSocial
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Coğrafya-2",
            onCorrectValueChange = {geographyCorrectForSocial = it},
            onFalseValueChange = {geographyFalseForSocial = it},
            correctValue = geographyCorrectForSocial,
            falseValue = geographyFalseForSocial
        )
        LessonCorrectAndFalseInputs(
            lessonTitle = "Felsefe",
            onCorrectValueChange = {philosophyCorrect = it},
            onFalseValueChange = {philosophyFalse = it},
            correctValue = philosophyCorrect,
            falseValue = philosophyFalse
        )

        LessonCorrectAndFalseInputs(
            lessonTitle = "Din Kültürü",
            onCorrectValueChange = {religionCorrect = it},
            onFalseValueChange = {religionFalse = it},
            correctValue = religionCorrect,
            falseValue = religionFalse
        )
        
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(PrimaryColor),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Kaydet")
        }
    }



}