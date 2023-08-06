package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.data.remote.dto.NewAytExamModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.secondaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@Composable
fun AytExamCard(
    aytExam: NewAytExamModel,
    navController: NavController
) {
    Card(
        colors = CardDefaults.cardColors(thirdColor),
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
            Text(
                text = "AYT",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .rotate(-90f)
                    .fillMaxHeight()
                    .weight(1f),
                color = secondaryColor
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = aytExam.examName,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

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
                            text = "Matematik:${aytExam.mathNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Fizik:${aytExam.physicsNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Kimya:${aytExam.chemistryNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Biyoloji:${aytExam.biologyNet}",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = "Sayısal Puan:${aytExam.numericalPoint}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Toplam Sayısal Net:${aytExam.mathNet + aytExam.physicsNet + aytExam.chemistryNet + aytExam.biologyNet}",
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
                        text = "Sayısal Puan:${aytExam.equalWeightPoint}",
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
                        text = "Söze Puan:${aytExam.verbalPoint}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Toplam Sözel Net:${aytExam.literatureNet + aytExam.religionNet + aytExam.philosophyNet + aytExam.historyForSocialNet}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }


            }




            IconButton(

                modifier = Modifier
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.ExamDetailScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight(),
                    tint = PrimaryColor
                )
            }
        }
    }
}
