package com.leventsurer.denemelerim.presentation.tyt_exam_detail.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.himanshoe.charty.pie.model.PieData
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable.CustomPieChart
import com.leventsurer.denemelerim.presentation.tyt_exam_detail.views.composable.TytExamLessonDetail
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TytExamDetail(
    tytExamDetail: NewTytExamModel,
    navController: NavController
) {

    val mathPieData = arrayListOf(
        PieData(
            tytExamDetail.mathFalse.toFloat(),
            "Yanlış Sayısı :${tytExamDetail.mathFalse}",
            Color.Red
        ),
        PieData(
            tytExamDetail.mathCorrect.toFloat(),
            "Doğru Sayısı :${tytExamDetail.mathCorrect}",
            Color.Green
        ),
        PieData(
            (40 - (tytExamDetail.mathCorrect + tytExamDetail.mathFalse)).toFloat(),
            "Boş Sayısı :${(40 - (tytExamDetail.mathCorrect + tytExamDetail.mathFalse))}",
            Color.Gray
        ),

        )
    val turkishPieData = arrayListOf(
        PieData(
            tytExamDetail.turkishFalse.toFloat(),
            "Yanlış Sayısı :${tytExamDetail.turkishFalse}",
            Color.Red
        ),
        PieData(
            tytExamDetail.turkishCorrect.toFloat(),
            "Doğru Sayısı :${tytExamDetail.turkishCorrect}",
            Color.Green
        ),
        PieData(
            (40 - (tytExamDetail.turkishCorrect + tytExamDetail.turkishFalse)).toFloat(),
            "Boş Sayısı :${(40 - (tytExamDetail.turkishCorrect + tytExamDetail.turkishFalse))}",
            Color.Gray
        ),
    )
    val socialPieData = arrayListOf(
        PieData(
            tytExamDetail.socialFalse.toFloat(),
            "Yanlış Sayısı :${tytExamDetail.socialFalse}", Color.Red
        ),
        PieData(
            tytExamDetail.socialCorrect.toFloat(),
            "Doğru Sayısı :${tytExamDetail.socialCorrect}",
            Color.Green
        ),
        PieData(
            (40 - (tytExamDetail.socialCorrect + tytExamDetail.socialFalse)).toFloat(),
            "Boş Sayısı :${(40 - (tytExamDetail.socialCorrect + tytExamDetail.socialFalse))}",
            Color.Gray
        ),
    )
    val socialAllFalseTopics = arrayListOf<String>()
    socialAllFalseTopics.addAll(tytExamDetail.geographyFalseTopicList)
    socialAllFalseTopics.addAll(tytExamDetail.historyFalseTopicList)
    socialAllFalseTopics.addAll(tytExamDetail.religionFalseTopicList)
    socialAllFalseTopics.addAll(tytExamDetail.philosophyFalseTopicList)

    val sciencePieData = arrayListOf(
        PieData(
            tytExamDetail.scienceFalse.toFloat(),
            "Yanlış Sayısı :${tytExamDetail.scienceFalse}", Color.Red
        ),
        PieData(
            tytExamDetail.scienceCorrect.toFloat(),
            "Doğru Sayısı :${tytExamDetail.scienceCorrect}",
            Color.Green
        ),
        PieData(
            (40 - (tytExamDetail.scienceCorrect + tytExamDetail.scienceFalse)).toFloat(),
            "Boş Sayısı :${(40 - (tytExamDetail.scienceCorrect + tytExamDetail.scienceFalse))}",
            Color.Gray
        ),
    )
    val scienceAllFalseTopics = arrayListOf<String>()
    socialAllFalseTopics.addAll(tytExamDetail.physicsFalseTopicList)
    socialAllFalseTopics.addAll(tytExamDetail.chemicalFalseTopicList)
    socialAllFalseTopics.addAll(tytExamDetail.biologyFalseTopicList)

    val totalExamPieData = arrayListOf(
        PieData(
            (tytExamDetail.turkishFalse + tytExamDetail.socialFalse + tytExamDetail.mathFalse + tytExamDetail.scienceFalse).toFloat(),
            "Yanlış Sayısı :${(tytExamDetail.turkishFalse + tytExamDetail.socialFalse + tytExamDetail.mathFalse + tytExamDetail.scienceFalse)}",
            Color.Red
        ),
        PieData(
            (tytExamDetail.turkishCorrect + tytExamDetail.socialCorrect + tytExamDetail.mathCorrect + tytExamDetail.scienceCorrect).toFloat(),
            "Doğru Sayısı :${(tytExamDetail.turkishCorrect + tytExamDetail.socialCorrect + tytExamDetail.mathCorrect + tytExamDetail.scienceCorrect)}",
            Color.Green
        ),
        PieData(
            (40 - (tytExamDetail.mathCorrect + tytExamDetail.mathFalse)).toFloat() + (40 - (tytExamDetail.turkishCorrect + tytExamDetail.turkishFalse)).toFloat() + (40 - (tytExamDetail.socialCorrect + tytExamDetail.socialFalse)).toFloat() + (40 - (tytExamDetail.scienceCorrect + tytExamDetail.scienceFalse)).toFloat(),
            "Boş Sayısı :${(40 - (tytExamDetail.scienceCorrect + tytExamDetail.scienceFalse))}",
            Color.Gray
        ),
    )
    val cardModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)

    val columnModifier: Modifier = Modifier.padding(5.dp)

    val instant = Instant.ofEpochSecond(
        tytExamDetail.examDate.seconds,
        tytExamDetail.examDate.nanoseconds.toLong()
    )
    val zoneId = ZoneId.systemDefault()
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val formattedDate = instant.atZone(zoneId).format(dateTimeFormatter)




    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Primary),
                title =
                {
                    Text(
                        "Sınav Detay",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )

                    }

                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = cardModifier,
                    colors = CardDefaults.cardColors(
                        Color.White
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    Column(modifier = columnModifier) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Sınav Adı", modifier = Modifier.weight(1f))
                            Text(text = tytExamDetail.examName, modifier = Modifier.weight(1f))
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Sınav Tarihi", modifier = Modifier.weight(1f))
                            Text(
                                text = formattedDate,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Toplam Puan", modifier = Modifier.weight(1f))
                            Text(
                                text = tytExamDetail.totalPoint.toString(),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Toplam Net", modifier = Modifier.weight(1f))
                            Text(
                                text = tytExamDetail.totalNet.toString(),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Text(text = "Sınav Hakkında Notlarım: ${tytExamDetail.aboutExam}", modifier = Modifier.fillMaxWidth())

                    }
                }
                TytExamLessonDetail(
                    cardModifier,
                    columnModifier,
                    turkishPieData,
                    tytExamDetail.turkishFalseTopicList,
                    "Türkçe"
                )

                TytExamLessonDetail(
                    cardModifier,
                    columnModifier,
                    socialPieData,
                    socialAllFalseTopics,
                    "Sosyal Bilgiler"
                )
                TytExamLessonDetail(
                    cardModifier,
                    columnModifier,
                    mathPieData,
                    tytExamDetail.mathFalseTopicList,
                    "Matematik"
                )
                TytExamLessonDetail(
                    cardModifier,
                    columnModifier,
                    sciencePieData,
                    scienceAllFalseTopics,
                    "Fen Bilgisi"
                )
                TytExamLessonDetail(
                    cardModifier,
                    columnModifier,
                    totalExamPieData,
                    null,
                    "Genel Sınav Grafiği"
                )


            }
        }
    )

}
