package com.leventsurer.denemelerim.presentation.exam_detail_screen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.MyTopAppBar

@Composable
fun ExamDetailScreen(navController: NavController) {

    Scaffold(
      topBar = { MyTopAppBar(appBarTitle = "Sınav Detay", navController = navController) }  ,
        content = {padding->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "345 Ayt Denemesi")
                Text(text = "Tür:AYT")
                Text(text = "Puan:412.321")
                Text(text = "Toplam Net:112.5")
                Divider()
                LessonDetail("Türkçe")
                LessonDetail("Matematik")
                LessonDetail("Sosyal")
                LessonDetail("Fen")

                ElevatedButton(
                    onClick = {

                }) {
                    Text(text = "Bilgileri Düzenle")
                }

                ElevatedButton(
                    onClick = {

                }) {
                    Text(text = "Sınavı Sil")
                }
            }
        }
    )

}

@Composable
fun LessonDetail(lessonName:String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)) {
        Text(text = lessonName)
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Doğru: 34")
            Text(text = "Boş: 6")
            Text(text = "Yanlış: 0")
        }


        Text(text = "Toplam Net: 34", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Divider()

    }
}