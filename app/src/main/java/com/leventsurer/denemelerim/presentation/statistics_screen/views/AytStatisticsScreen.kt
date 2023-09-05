package com.leventsurer.denemelerim.presentation.statistics_screen.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable.ShowOrHideExamType
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileEvent
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileViewModel
import com.leventsurer.denemelerim.presentation.statistics_screen.StatisticsEvent
import com.leventsurer.denemelerim.presentation.statistics_screen.StatisticsViewModel
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.LineChart
import com.leventsurer.denemelerim.presentation.statistics_screen.composable.MyBarChart
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun AytStatisticsScreen(
    statisticsViewModel: StatisticsViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    var isNumericalStatisticsChecked by remember {
        mutableStateOf(false)
    }
    var isEqualWeightStatisticsChecked by remember {
        mutableStateOf(false)
    }
    var isVerbalStatisticsChecked by rememberSaveable {
        mutableStateOf(false)
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))

    val statisticsState = statisticsViewModel.statisticsState.value
    val profileState = profileViewModel.getUserProfileInfoState.value

    val mathTotalNetList = arrayListOf<Float>()
    val physicsTotalNetList = arrayListOf<Float>()
    val chemistryTotalNetList = arrayListOf<Float>()
    val biologyTotalNetList = arrayListOf<Float>()

    val literatureTotalNetList = arrayListOf<Float>()
    val historyTotalNetList = arrayListOf<Float>()
    val geographyTotalNetList = arrayListOf<Float>()


    val historyForSocialTotalNetList = arrayListOf<Float>()
    val geographyForSocialTotalNetList = arrayListOf<Float>()
    val philosophy = arrayListOf<Float>()
    val religion = arrayListOf<Float>()

    val totalNumericalNetList = arrayListOf<Float>()
    val totalEqualWeightNetList = arrayListOf<Float>()
    val totalVerbalNetList = arrayListOf<Float>()

    LaunchedEffect(Unit) {
        statisticsViewModel.onEvent(
            StatisticsEvent.GetAytExams(
                dataStoreViewModel.getUserUidFromDataStore()
            )
        )
    }

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        if (statisticsState.isLoading) {
            LottieAnimation(
                modifier = Modifier.fillMaxWidth(),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        } else {

            if (statisticsState.aytExams != null) {
                LaunchedEffect(Unit) {
                    profileViewModel.onEvent(
                        ProfileEvent.GetUserProfileExam(
                            dataStoreViewModel.getUserUidFromDataStore()
                        )
                    )
                }
                if (profileState.resultWithExam != null) {
                    for (exam in profileState.resultWithExam!!.aytNumericalNetList) {
                        totalNumericalNetList.add(exam.toFloat())
                    }
                    Log.e("kontrol", "sayısal ağırlık netleri:${totalNumericalNetList}")
                    for (exam in profileState.resultWithExam!!.aytEqualWeightNetList) {
                        totalEqualWeightNetList.add(exam.toFloat())
                    }
                    Log.e("kontrol", "eşit ağırlık netleri:${totalEqualWeightNetList}")
                    for (exam in profileState.resultWithExam!!.aytVerbalNetList) {
                        totalVerbalNetList.add(exam.toFloat())
                    }
                    Log.e("kontrol", "sözel netleri:${totalVerbalNetList}")

                }
                if (statisticsState.aytExams.isEmpty()) {
                    Text(text = "Gösterilecek bir istatistik bulunmuyor.")

                } else {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isNumericalStatisticsChecked = !isNumericalStatisticsChecked
                            }
                    ) {
                        Text(text = "Sayısal İstatistikleri")
                        ShowOrHideExamType(isNumericalStatisticsChecked) {isNumericalStatisticsChecked = it}
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isEqualWeightStatisticsChecked = !isEqualWeightStatisticsChecked
                            }
                    ) {
                        Text(text = "Eşit Ağırlık İstatistikleri")
                        ShowOrHideExamType(isEqualWeightStatisticsChecked) {isEqualWeightStatisticsChecked = it}

                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isVerbalStatisticsChecked = !isVerbalStatisticsChecked
                            }
                    ) {
                        Text(text = "Sözel İstatistikleri")
                        ShowOrHideExamType(isVerbalStatisticsChecked) { isVerbalStatisticsChecked = it }

                    }
                    for (exam in statisticsState.aytExams) {
                        mathTotalNetList.add(exam.mathNet.toFloat())
                        physicsTotalNetList.add(exam.physicsNet.toFloat())
                        chemistryTotalNetList.add(exam.chemistryNet.toFloat())
                        biologyTotalNetList.add(exam.biologyNet.toFloat())
                        literatureTotalNetList.add(exam.literatureNet.toFloat())
                        historyTotalNetList.add(exam.historyNet.toFloat())
                        geographyTotalNetList.add(exam.geographyNet.toFloat())
                        historyForSocialTotalNetList.add(exam.historyForSocialNet.toFloat())
                        geographyForSocialTotalNetList.add(exam.geographyForSocialNet.toFloat())
                        philosophy.add(exam.philosophyNet.toFloat())
                        religion.add(exam.religionNet.toFloat())
                    }
                    if (isNumericalStatisticsChecked) {
                        Column {
                            Text(
                                text = "Sayısal İstatistikler",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (profileState.resultWithExam != null) {
                                Text(text = "Genel Toplam Net")
                                MyBarChart(totalNumericalNetList)

                            }

                            Text(text = "Matematik Toplam Net")
                            MyBarChart(mathTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Fizik Toplam Net")
                            MyBarChart(mathTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Kimya Toplam Net")
                            MyBarChart(chemistryTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Biyoloji Toplam Net")
                            MyBarChart(biologyTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                        }
                    }
                    if (isEqualWeightStatisticsChecked) {
                        Column {
                            Text(
                                text = "Eşit Ağırlık İstatistikler",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (profileState.resultWithExam != null) {
                                Text(text = "Genel Toplam Net")
                                MyBarChart(totalEqualWeightNetList)
                            }
                            Text(text = "Matematik Toplam Net")
                            MyBarChart(mathTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Edebiyat Toplam Net")
                            MyBarChart(literatureTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Tarih Toplam Net")
                            MyBarChart(historyTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Coğrafya Toplam Net")
                            MyBarChart(geographyTotalNetList)
                        }
                    }
                    if (isVerbalStatisticsChecked) {
                        Column {
                            Text(
                                text = "Sözel İstatistikler",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            if (profileState.resultWithExam != null) {
                                Text(text = "Genel Toplam Net")
                                MyBarChart(totalVerbalNetList)
                            }
                            Text(text = "Edebiyat Toplam Net")
                            MyBarChart(literatureTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Tarih Toplam Net")
                            MyBarChart(historyTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Coğrafya Toplam Net")
                            MyBarChart(geographyTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Tarih-2 Toplam Net")
                            MyBarChart(historyForSocialTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Coğrafya-2 Toplam Net")
                            MyBarChart(geographyForSocialTotalNetList)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "Felsefe Toplam Net")
                            MyBarChart(philosophy)
                            Divider(modifier = Modifier.height(1.dp))
                            Text(text = "D.K.V.A.B Toplam Net")
                            MyBarChart(religion)

                        }
                    }
                }
            }
        }
    }
}


