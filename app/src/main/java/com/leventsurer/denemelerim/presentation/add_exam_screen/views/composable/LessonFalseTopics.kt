package com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp

@Composable
fun LessonFalseTopics(lessonName:String, allLessonTopicsList:ArrayList<String>,falseTopicList:MutableList<String>) {
    var isExtended by remember {
        mutableStateOf(false)
    }


    Column() {
        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .clickable {
                    isExtended = !isExtended
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = lessonName)
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "arrow")
                if (isExtended) {
                    for (topic in allLessonTopicsList) {
                        var isChecked by remember {
                            mutableStateOf(topic in falseTopicList)
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isChecked = !isChecked
                                    if(isChecked){
                                        falseTopicList.add(topic)
                                        Log.e("kontrol","içerdeki list:${falseTopicList}")
                                    }else{
                                        falseTopicList.remove(topic)
                                    }
                                },
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = topic)
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = {
                                        isChecked = !isChecked
                                        if(isChecked){
                                            falseTopicList.add(topic)
                                            Log.e("kontrol","içerdeki list:${falseTopicList}")
                                        }else{
                                            falseTopicList.remove(topic)
                                        }
                                    })


                            }
                            Divider()
                        }
                    }



                }

            }
        }


    }
}