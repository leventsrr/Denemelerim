package com.leventsurer.denemelerim.presentation.add_exam_screen.views.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpinner(
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("TYT", "AYT")
    var chosenExamType by remember {
        mutableStateOf("Sınav Tipi")
    }
    Box {
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
            onClick = { expanded = !expanded }){
            Text (chosenExamType)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        chosenExamType = label
                        onClick(chosenExamType)
                    },
                    text = {
                        Text(text = label)
                    }
                )
            }
        }
    }
}