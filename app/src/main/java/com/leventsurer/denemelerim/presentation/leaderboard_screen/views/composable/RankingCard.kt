package com.leventsurer.denemelerim.presentation.leaderboard_screen.views.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
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
            Column(modifier = Modifier.weight(8f)) {
                Text(text = userModel.userName, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Divider(color = Color.White)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Deneme Sayısı (AYT)", fontWeight = FontWeight.Bold)
                    Text(text = userModel.numberOfAytExam.toString())
                }
                LeaderBoardCardPointRow("Sayısal Yks Puanı",userModel.numericalYksExamPoint)
                LeaderBoardCardPointRow("Eşit Ağırlık Yks Puanı",userModel.equalWeightYksExamPoint)
                LeaderBoardCardPointRow("Sözel Yks Puanı",userModel.verbalYksExamPoint)
            }
        }
    }
}

