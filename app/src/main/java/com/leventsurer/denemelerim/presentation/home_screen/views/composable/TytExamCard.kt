package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.Fifth
import com.leventsurer.denemelerim.presentation.ui.theme.Fourth
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.ui.theme.Third
import java.util.Locale

@Composable
fun TytExamCard(
    tytExam: NewTytExamModel,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Card(
        colors = CardDefaults.cardColors(Color.White),

        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)


        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = tytExam.examName.uppercase(Locale.ROOT),
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Toplam Net:")
                        Text(text = String.format("%.2f", tytExam.totalNet))
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Puan", modifier = Modifier.weight(1f))
                        Text(
                            text = ": ${String.format("%.3f", tytExam.totalPoint)}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TytLessonNetCardColumn(
                        Modifier
                            .weight(1f)
                            .padding(start = 10.dp),
                        "Türkçe",
                        tytExam.turkishNet.toString(),
                        "Fen",
                        tytExam.scienceNet.toString()
                    )
                    TytLessonNetCardColumn(
                        Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        "Matematik",
                        tytExam.mathNet.toString(),
                        "Sosyal",
                        tytExam.socialNet.toString()
                    )
                }
                IconButton(
                    onClick = {



                        val tytExamDetailJson = Gson().toJson(tytExam)
                        navController.navigate(
                            "${Screen.TytExamDetail.route}/$tytExamDetailJson"
                        )
                    }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Exam Detail",
                        tint = Secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp)
                    )
                }


            }

        }
    }
}

