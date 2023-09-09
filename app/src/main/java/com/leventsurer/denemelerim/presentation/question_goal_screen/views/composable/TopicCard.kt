package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TopicCard(topicName:String) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { isChecked = !isChecked },) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = topicName)
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = !isChecked })


        }
        Divider()
    }
}

