package com.leventsurer.denemelerim.presentation.leaderboard_screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leventsurer.denemelerim.presentation.ui.theme.goldColor
import com.leventsurer.denemelerim.presentation.ui.theme.platinumColor
import com.leventsurer.denemelerim.presentation.ui.theme.silverColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@Composable
fun RankingCard(rank:Int) {
    val backgroundColor = when (rank) {
        1 -> {
            goldColor
        }
        2 -> {
            silverColor
        }
        3 -> {
            platinumColor
        }
        else->{
            thirdColor
        }
    }
    Card(
        colors = CardDefaults.cardColors(backgroundColor),
        //border = BorderStroke(borderStroke,backgroundColor),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {

            Text(text = rank.toString(), textAlign = TextAlign.Center,modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, )

            Column(modifier = Modifier.weight(6f)) {

                Text(text = "Levent Sürer", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)


                Text(text = "Deneme Sayısı: 26")
                Text(text = "Ortalama Sınav Puanı: 416.235")
                Text(text = "Ortalama Net Sayısı: 112.5")

            }
        }
    }
}