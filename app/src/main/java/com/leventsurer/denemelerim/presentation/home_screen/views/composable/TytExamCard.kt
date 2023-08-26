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
import com.leventsurer.denemelerim.data.remote.dto.NewTytExamModel
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.secondaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@Composable
fun TytExamCard(
    tytExam: NewTytExamModel,
    navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(thirdColor),
        //border = BorderStroke(2.dp, PrimaryColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)

    ){
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Text(text = "TYT", textAlign = TextAlign.Center, fontWeight = FontWeight.ExtraBold,modifier= Modifier
                .rotate(-90f)
                .fillMaxHeight()
                .weight(1f),
                color = secondaryColor
            )
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = tytExam.examName, fontWeight = FontWeight.ExtraBold )
                    Text(text = tytExam.totalPoint.toString(), fontWeight = FontWeight.ExtraBold, )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Türkçe:${tytExam.turkishNet}" )
                    Text(text = "Matematik:${tytExam.mathNet}" )
                }
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Sosyal:${tytExam.socialNet}" )
                    Text(text = "Fen:${tytExam.scienceNet}" )
                }
                Text(text = "Toplam ${tytExam.totalNet}.", fontWeight = FontWeight.ExtraBold)


            }
            /*IconButton(

                modifier = Modifier
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.ExamDetailScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier.fillMaxHeight(), tint = PrimaryColor)
            }*/
        }
    }
}
