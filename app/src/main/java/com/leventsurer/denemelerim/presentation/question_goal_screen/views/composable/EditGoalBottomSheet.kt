package com.leventsurer.denemelerim.presentation.question_goal_screen.views.composable

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.question_goal_screen.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGoalBottomSheet(questionGoalModel: QuestionGoalModel, onDismiss: () -> Unit) {

    val goalQuestionViewModel:GoalQuestionViewModel = hiltViewModel()

    val datastoreViewModel:DataStoreViewModel = hiltViewModel()
    val goalQuestionViewModelState = goalQuestionViewModel.updateGoalState.value
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
                readOnly = true  ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = questionGoalModel.goalQuestionQuantity.toString(),
                onValueChange = {

                },
                label = { Text("Hedef Soru Sayısı") },

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

            if(isError){
                Text(text = "Değerler Toplamı Hedef Sayıyı Geçemez", color = Color.Red)
            }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                onClick = {
                    val newSolvedQuestionQuantity = (correctSolvedQuestionQuantity.toIntOrNull() ?: 0) + (falseSolvedQuestionQuantity.toIntOrNull()?:0)

                    val questionGoal = QuestionGoalModel(
                        goalName = questionGoalModel.goalName,
                        goalQuestionQuantity = questionGoalModel.goalQuestionQuantity,
                        solvedQuestionQuantity =newSolvedQuestionQuantity,
                        rightQuestionQuantity = correctSolvedQuestionQuantity.toIntOrNull()?:0,
                        falseQuestionQuantity = falseSolvedQuestionQuantity.toIntOrNull()?:0,
                        emptyQuestionQuantity = questionGoalModel.goalQuestionQuantity - newSolvedQuestionQuantity
                    )

                    if((falseSolvedQuestionQuantity.toIntOrNull()?:0) + (correctSolvedQuestionQuantity.toIntOrNull()?:0) > questionGoalModel.goalQuestionQuantity){
                        isError = true
                    }else{
                        goalQuestionViewModel.onEvent(QuestionGoalEvent.UpdateQuestionGoal(
                            questionGoalModel = questionGoal,
                            userUid = datastoreViewModel.getUserUidFromDataStore()
                        ))

                        /*goalQuestionViewModel.onEvent(
                            QuestionGoalEvent.GetQuestionGoals(
                                datastoreViewModel.getUserUidFromDataStore()
                            )
                        )*/
                    }





                }) {
                if(goalQuestionViewModelState.isLoading){
                    CircularProgressIndicator()
                }else{
                    Text(text = "kaydet")
                }

            }
        }
    }

}