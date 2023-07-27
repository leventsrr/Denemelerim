package com.leventsurer.denemelerim.presentation.leaderboard_screen.views

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.leventsurer.denemelerim.presentation.common.MyTopAppBar
import com.leventsurer.denemelerim.presentation.leaderboard_screen.composable.RankingCard
import com.leventsurer.denemelerim.presentation.ui.theme.PrimaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.fourthColor
import com.leventsurer.denemelerim.presentation.ui.theme.goldColor
import com.leventsurer.denemelerim.presentation.ui.theme.platinumColor
import com.leventsurer.denemelerim.presentation.ui.theme.secondaryColor
import com.leventsurer.denemelerim.presentation.ui.theme.silverColor
import com.leventsurer.denemelerim.presentation.ui.theme.thirdColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeaderboardScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.LightGray,
        topBar = { MyTopAppBar(appBarTitle = "Sıralamam", navController = navController) },
        content = { padding->
            Column(modifier = Modifier.padding(padding), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Gazi Üniversitesi", fontWeight = FontWeight.ExtraBold, color = PrimaryColor, fontSize = 30.sp)
                Text(text = "Bilgisayar mühendisliği", fontWeight = FontWeight.Bold, color = fourthColor, fontSize = 25.sp)
                LazyColumn(){
                    items(13){index->
                        RankingCard(rank = index+1 )
                    }
                }
            }
        }

    )
}

