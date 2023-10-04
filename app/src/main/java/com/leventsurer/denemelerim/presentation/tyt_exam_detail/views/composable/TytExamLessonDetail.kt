package com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.himanshoe.charty.pie.model.PieData


@Composable
fun TytExamLessonDetail(
    cardModifier: Modifier,
    columnModifier: Modifier,
    pieData: ArrayList<PieData>,
    lessonFalseTopics: ArrayList<String>?,
    lessonName: String
) {
    Card(
        modifier = cardModifier, elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            Color.White
        )
    ) {
        Column(modifier = columnModifier) {
            Text(
                text = "$lessonName Sonuçları",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            CustomPieChart(pieData)
            if(!lessonFalseTopics.isNullOrEmpty()){
                Divider()
                Text(text = "Yanlış Yapılan Konular", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                for (falseTopic in lessonFalseTopics) {
                    Text(text = "- $falseTopic")
                }
            }



        }
    }
}