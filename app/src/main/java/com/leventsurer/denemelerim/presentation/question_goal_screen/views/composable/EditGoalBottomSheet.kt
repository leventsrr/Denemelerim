package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGoalBottomSheet(onDismiss: () -> Unit) {


        val modalBottomSheetState = rememberModalBottomSheetState()
        var solvedQuestionQuantity by remember {
            mutableStateOf("")
        }

        var correctSolvedQuestionQuantity by remember {
            mutableStateOf("")
        }

        var falseSolvedQuestionQuantity by remember {
            mutableStateOf("")
        }
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = modalBottomSheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = solvedQuestionQuantity,
                    onValueChange = {
                        solvedQuestionQuantity = it
                    },
                    label = { Text("Çözülen Soru") },

                    )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = correctSolvedQuestionQuantity,
                    onValueChange = {
                        correctSolvedQuestionQuantity = it
                    },
                    label = { Text("Doğru Sayısı") },

                    )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = falseSolvedQuestionQuantity,
                    onValueChange = {
                        falseSolvedQuestionQuantity = it
                    },
                    label = { Text("Yanlış Sayısı") },

                    )
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                    onClick = { /*TODO*/ }) {
                    Text(text = "kaydet")
                }
            }
        }

}