package com.leventsurer.denemelerim.presentation.profile_screen.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.profile_screen.ProfileState
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@Composable
fun ProfileInformationCard(state: ProfileState){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.data))
    var isExpanded by remember{ mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing,
                )
            )
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clickable { isExpanded = !isExpanded },

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = "Profil Bilgileri",
                color = Secondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                textAlign = TextAlign.Center
            )
            Divider()
            if (state.result == null) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    LottieAnimation(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                }
            } else {
                if(!isExpanded){
                    Text(text = "Kullanıcı Adı:${state.result.userName}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Çözülen TYT Deneme Sayısı: ${state.result.numberOfTytExam}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Çözülen AYT Deneme Sayısı: ${state.result.numberOfAytExam}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Hedef Üniversite: ${state.result.targetUniversity}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Hedef Bölüm: ${state.result.targetDepartment}")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "", tint = Primary, modifier = Modifier.fillMaxWidth())
                }else{
                    Text(text = "Kullanıcı Adı:${state.result.userName}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Çözülen TYT Deneme Sayısı: ${state.result.numberOfTytExam}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Çözülen AYT Deneme Sayısı: ${state.result.numberOfAytExam}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Hedef Üniversite: ${state.result.targetUniversity}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Hedef Bölüm: ${state.result.targetDepartment}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama TYT Puanı: ${String.format("%.3f",state.result.averageTytPoint)}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama Sayısal AYT Puanı: ${String.format("%.3f",state.result.averageNumericalPoint)  }")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama E.A AYT Puanı: ${String.format("%.3f",state.result.averageEqualWeightPoint) }")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama Sözel AYT Puanı: ${String.format("%.3f",state.result.averageVerbalPoint)}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama Sayısal YKS Puanı: ${String.format("%.3f",state.result.numericalYksPoint)}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama Eşit Ağırlık YKS Puanı:${String.format("%.3f",state.result.equalWeightYksPoint)}")
                    Divider(modifier = Modifier.height(1.dp))
                    Text(text = "Ortalama Sözel YKS Puanı: ${String.format("%.3f",state.result.verbalYksPoint)}")
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "", tint = Primary, modifier = Modifier.fillMaxWidth())
                }



            }
        }
    }
}