package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun AytExamCard(
    aytExam: NewAytExamModel,
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(20.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable { isExpanded = !isExpanded },

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

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
                    text = aytExam.examName.uppercase(),
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp), color = Color.White)
                if (!isExpanded) {
                    Row() {
                        Text(
                            text = "Toplam Sayısal Net",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = ": ${
                                String.format(
                                    "%.2f",
                                    aytExam.mathNet + aytExam.physicsNet + aytExam.chemistryNet + aytExam.biologyNet
                                )
                            }",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                    }
                    Row() {
                        Text(
                            text = "Toplam Eşit Ağırlık Net",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = ": ${aytExam.mathNet + aytExam.literatureNet + aytExam.geographyNet + aytExam.historyNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                    }
                    Row() {
                        Text(
                            text = "Toplam Sözel Net",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = ": ${aytExam.literatureNet + aytExam.religionNet + aytExam.philosophyNet + aytExam.historyForSocialNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                    }


                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                        tint = Secondary
                    )


                } else {


                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                        Text(
                            text = "Sayısal Sonuç",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        AytLessonNetCardRow(
                            "Matematik",
                            aytExam.mathNet,
                            "Fizik",
                            aytExam.physicsNet
                        )

                        AytLessonNetCardRow(
                            "Kimya",
                            aytExam.chemistryNet,
                            "Biyoloji",
                            aytExam.biologyNet
                        )

                        Row() {
                            Text(
                                text = "Sayısal Puan:${
                                    String.format(
                                        "%.3f",
                                        aytExam.numericalPoint
                                    )
                                }",
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "Toplam Net:${
                                    String.format(
                                        "%.2f",
                                        aytExam.mathNet + aytExam.physicsNet + aytExam.chemistryNet + aytExam.biologyNet
                                    )
                                }",
                                modifier = Modifier.weight(1f),
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                        Text(
                            text = "Eşit Ağırlık Sonuç",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        AytLessonNetCardRow(
                            "Matematik",
                            aytExam.mathNet,
                            "Fizik",
                            aytExam.literatureNet
                        )
                        AytLessonNetCardRow(
                            "Tarih",
                            aytExam.historyNet,
                            "Coğrafya",
                            aytExam.geographyNet
                        )

                        Row {
                            Text(
                                text = "Eşit Ağırlık Puan:${
                                    String.format(
                                        "%.3f",
                                        aytExam.equalWeightPoint
                                    )
                                }",
                                modifier = Modifier.weight(1f),

                                )
                            Text(
                                text = "Toplam Net:${aytExam.mathNet + aytExam.literatureNet + aytExam.geographyNet + aytExam.historyNet}",
                                modifier = Modifier.weight(1f),
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                        Text(
                            text = "Sözel Sonuç",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        AytLessonNetCardRow(
                            "Edebiyat",
                            aytExam.literatureNet,
                            "Tarih",
                            aytExam.historyForSocialNet
                        )
                        AytLessonNetCardRow(
                            "Felsefe",
                            aytExam.philosophyNet,
                            "D.K.A.B",
                            aytExam.religionNet
                        )
                        Row() {
                            Text(
                                text = "Sözel Puan:${String.format("%.3f", aytExam.verbalPoint)}",
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "Toplam Net:${aytExam.literatureNet + aytExam.religionNet + aytExam.philosophyNet + aytExam.historyForSocialNet}",
                                modifier = Modifier.weight(1f),
                            )
                        }

                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "",
                        tint = Secondary
                    )


                }

            }
        }
    }

}

