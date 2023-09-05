package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.presentation.ui.theme.Primary
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

//Show bottom sheet source :https://medium.com/@manojbhadane/mastering-android-jetpack-compose-bottomsheet-with-material-3-e61af75c0cac
@Composable
fun QuestionGoalCard(goalName:String,goalQuestionQuantity:Int,solvedQuestionQuantity:Int,rightQuestionQuantity:Int,falseQuestionQuantity:Int) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        EditGoalBottomSheet() {
            showSheet = false
        }
    }


    Card(
        //elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing,
                )
            )
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = goalName)
                    IconButton(
                        onClick = {

                            showSheet = true
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_edit_note_24),
                            tint = Secondary,
                            contentDescription = "asdasd"
                        )
                    }
                }
                CustomLinearProgressIndicator(solvedQuestionQuantity.toFloat()/goalQuestionQuantity.toFloat())
            }
            if (isExpanded) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Hedeflenen Soru Sayısı")
                    Text(text = goalQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Çözülen Soru Sayısı")
                    Text(text = solvedQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Doğru Sayısı")
                    Text(text = rightQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Yanlış Sayısı")
                    Text(text = falseQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Boş Sayısı")
                    Text(text = (goalQuestionQuantity- (rightQuestionQuantity + falseQuestionQuantity)).toString())
                }
            }
            if(isExpanded){
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Arrow Down", modifier = Modifier.fillMaxWidth())
            }else{
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Arrow Down", modifier = Modifier.fillMaxWidth())
            }

        }
    }
}
