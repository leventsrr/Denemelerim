package com.leventsurer.denemelerim.presentation.leaderboard_screen.composable

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leventsurer.denemelerim.data.remote.dto.UserModel
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import com.leventsurer.denemelerim.presentation.ui.theme.Third
import com.leventsurer.denemelerim.presentation.ui.theme.goldColor
import com.leventsurer.denemelerim.presentation.ui.theme.platinumColor
import com.leventsurer.denemelerim.presentation.ui.theme.silverColor

@Composable
fun RankingCard(rank:Int,userModel: UserModel) {


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
            Color(0x755B85AA)
        }
    }
    Card(
        colors = CardDefaults.cardColors(backgroundColor),
        shape = RoundedCornerShape(20.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            Text(text = rank.toString(), textAlign = TextAlign.Center,modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, color = Secondary )
            Column(modifier = Modifier.weight(6f)) {
                Text(text = userModel.userName, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "Deneme Sayısı: ${userModel.numberOfAytExam}", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Ortalama Sayısal Yks Puanı:", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text(text = String.format("%.3f",userModel.numericalYksExamPoint), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Ortalama Eşit Ağırlık Yks Puanı:", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text(text = String.format("%.3f",userModel.equalWeightYksExamPoint), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Ortalama Sözel Yks Puanı:",textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text(text = String.format("%.3f",userModel.verbalYksExamPoint), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }

            }
        }
    }
}