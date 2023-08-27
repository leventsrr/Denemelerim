package com.leventsurer.denemelerim.presentation.statistics_screen.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.add_exam_screen.AddExamEvent
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.statistics_screen.StatisticsEvent
import com.leventsurer.denemelerim.presentation.statistics_screen.StatisticsViewModel
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart

@Composable
fun TytStatisticsScreen(
    statisticsViewModel: StatisticsViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))

    val statisticsState = statisticsViewModel.statisticsState.value


    val mathTotalNetList = arrayListOf<Float>()
    val turkishTotalNetList = arrayListOf<Float>()
    val socialTotalNetList = arrayListOf<Float>()
    val scienceTotalNetList = arrayListOf<Float>()
    LaunchedEffect(Unit) {

        statisticsViewModel.onEvent(
            StatisticsEvent.GetTytExams(
                dataStoreViewModel.getUserUidFromDataStore()
            )
        )


    }

    if(statisticsState.isLoading){
        LottieAnimation(
            modifier = Modifier.fillMaxWidth(),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }else if(statisticsState.tytExams!=null){
        if(statisticsState.tytExams.isEmpty()){
            Text(text = "Henüz kayıtlı istatistiğiniz bulunmuyor")
        }else{
            for(exam in statisticsState.tytExams){
                mathTotalNetList.add(exam.mathNet.toFloat())
                turkishTotalNetList.add(exam.turkishNet.toFloat())
                socialTotalNetList.add(exam.socialNet.toFloat())
                scienceTotalNetList.add(exam.scienceNet.toFloat())
            }
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = "Genel Toplam Net Bazlı Grafik",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                LineChart(scienceTotalNetList)
                //Genel Net Bazlı
                Text(
                    text = "Ders Bazlı Grafik",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = "Türkçe", fontSize = 15.sp)
                LineChart(turkishTotalNetList)
                Divider(modifier = Modifier.height(1.dp))
                Text(text = "Matematik", fontSize = 15.sp)
                LineChart(mathTotalNetList)
                Divider(modifier = Modifier.height(1.dp))
                Text(text = "Sosyal", fontSize = 15.sp)
                LineChart(socialTotalNetList)
                Divider(modifier = Modifier.height(1.dp))
                Text(text = "Fen", fontSize = 15.sp)
                LineChart(scienceTotalNetList)
            }
        }
    }


}