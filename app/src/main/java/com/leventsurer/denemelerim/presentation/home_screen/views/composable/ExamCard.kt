package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.ui.Screen
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.fourthColor
import com.leventsurer.denemelerim.presentation.ui.theme.secondaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@Composable
fun ExamCard(navController: NavController) {
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
                    Text(text = "345 Orta Denemesi", fontWeight = FontWeight.ExtraBold )
                    Text(text = "415.531", fontWeight = FontWeight.ExtraBold, )
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Türkçe 36", )
                    Text(text = "Sosyal 15", )
                }
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = "Matemik 32",  )
                    Text(text = "Fen 17", )
                }
                Text(text = "Toplam: 111", fontWeight = FontWeight.ExtraBold)


            }
            IconButton(

                modifier = Modifier
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.ExamDetailScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier.fillMaxHeight(), tint = PrimaryColor)
            }
        }
    }
}
