package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.ui.theme.Third

@Composable
fun AytExamCard(
    aytExam: NewAytExamModel,
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(Color(0x755B85AA)),
        shape = RoundedCornerShape(20.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing,
                )
            )
            .clickable { isExpanded = !isExpanded }

    ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "AYT",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .rotate(-90f)
                        .fillMaxHeight()
                        .weight(1f),
                    color = Secondary
                )
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
                    Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                    if(!isExpanded){

                            Text(
                                text = "Toplam Sayısal Net:${String.format("%.2f", aytExam.mathNet + aytExam.physicsNet + aytExam.chemistryNet + aytExam.biologyNet)  }",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = "Toplam Eşit Ağırlık Net:${aytExam.mathNet + aytExam.literatureNet + aytExam.geographyNet + aytExam.historyNet}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = "Toplam Sözel Net:${aytExam.literatureNet + aytExam.religionNet + aytExam.philosophyNet + aytExam.historyForSocialNet}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )

                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "", tint = Color.White)


                    }else{


                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Sayısal Sonuç",
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),

                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Matematik:${String.format("%.2f", aytExam.mathNet)}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Fizik:${ String.format("%.2f", aytExam.physicsNet)}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Kimya:${String.format("%.2f", aytExam.chemistryNet)}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Biyoloji:${String.format("%.2f", aytExam.biologyNet)}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = "Sayısal Puan:${String.format("%.3f", aytExam.numericalPoint)}",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Toplam Sayısal Net:${String.format("%.2f", aytExam.mathNet + aytExam.physicsNet + aytExam.chemistryNet + aytExam.biologyNet)  }",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Eşit Ağırlık Sonuç",
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Matematik:${aytExam.mathNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Fizik:${aytExam.literatureNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Kimya:${aytExam.historyNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Biyoloji:${aytExam.geographyNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = "Sayısal Puan:${ String.format("%.3f",  aytExam.equalWeightPoint) }",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Toplam Eşit Ağırlık Net:${aytExam.mathNet + aytExam.literatureNet + aytExam.geographyNet + aytExam.historyNet}",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Sözel Sonuç",
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Edebiyat:${aytExam.literatureNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Tarih:${aytExam.historyForSocialNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = "Felsefe:${aytExam.philosophyNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "D.K.A.B:${aytExam.religionNet}",
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = "Sözel Puan:${ String.format("%.3f", aytExam.verbalPoint)}",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Toplam Sözel Net:${aytExam.literatureNet + aytExam.religionNet + aytExam.philosophyNet + aytExam.historyForSocialNet}",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }

                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "", tint = Color.White)


                    }

                }
            }
        }

    }
