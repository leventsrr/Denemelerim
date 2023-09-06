package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewQuestionGoalBottomSheet(onDismiss: () -> Unit) {

    val questionGoalViewModel: GoalQuestionViewModel = hiltViewModel()
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
    val addQuestionGoalState = questionGoalViewModel.addQuestionGoalState.value
    val modalBottomSheetState = rememberModalBottomSheetState()
    var goalQuestionQuantity by remember {
        mutableStateOf("")
    }

    var goalName by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = goalName,
                onValueChange = {
                    goalName = it
                },
                label = { Text("Hedef Adı") },

                )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = goalQuestionQuantity,
                onValueChange = {
                    goalQuestionQuantity = it
                },
                label = { Text("Hedef Soru Sayısı") },

                )
            
            if(isError){
              Text(text = "Hedef Adı Boş Bırakılamaz.", color = Color.Red)
            }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                onClick = {

                    val questionModel = QuestionGoalModel(
                        goalName = goalName,
                        goalQuestionQuantity = goalQuestionQuantity.toIntOrNull()?:0
                    )
                    if(goalName.isNullOrEmpty()){
                        isError = true
                    }else{
                        questionGoalViewModel.onEvent(
                            QuestionGoalEvent.AddNewQuestionGoal(
                                questionModel,
                                dataStoreViewModel.getUserUidFromDataStore()
                            )
                        )

                        questionGoalViewModel.onEvent(
                            QuestionGoalEvent.GetQuestionGoals(
                                dataStoreViewModel.getUserUidFromDataStore(),
                            )
                        )
                    }


                    if (!addQuestionGoalState.isLoading) {
                        goalName = ""
                        goalQuestionQuantity = ""
                    }




                }) {

                Text(text = "kaydet")

            }
        }
    }

}