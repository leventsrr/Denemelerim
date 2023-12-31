package com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.views.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.leventsurer.denemelerim.R
import com.leventsurer.denemelerim.data.remote.dto.QuestionGoalModel
import com.leventsurer.denemelerim.presentation.common.data_store.DataStoreViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.GoalQuestionViewModel
import com.leventsurer.denemelerim.presentation.tracking_screen.question_goals.QuestionGoalEvent
import com.leventsurer.denemelerim.presentation.ui.theme.Secondary

//Show bottom sheet source :https://medium.com/@manojbhadane/mastering-android-jetpack-compose-bottomsheet-with-material-3-e61af75c0cac
@Composable
fun QuestionGoalCard(
    questionGoalModel: QuestionGoalModel,
    deleteItem: (QuestionGoalModel) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var showSheet by remember { mutableStateOf(false) }
    val goalQuestionViewModel: GoalQuestionViewModel = hiltViewModel()
    val dataStoreViewModel:DataStoreViewModel = hiltViewModel()

    if (showSheet) {
        EditGoalBottomSheet(questionGoalModel) {
            showSheet = false
        }
    }


    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        //border = BorderStroke(1.dp, Primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)

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
                    Text(text = questionGoalModel.goalName)
                    Row() {
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

                        IconButton(
                            modifier = Modifier.width(20.dp),
                            onClick = {
                                goalQuestionViewModel.onEvent(
                                    QuestionGoalEvent.DeleteQuestionGoal(
                                    questionGoalModel,
                                    dataStoreViewModel.getUserUidFromDataStore()
                                ))

                                deleteItem(questionGoalModel)



                            }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete goal",
                                tint = Color.Red
                            )
                        }
                    }
                }
                CustomLinearProgressIndicator(questionGoalModel.solvedQuestionQuantity.toFloat() / questionGoalModel.goalQuestionQuantity.toFloat())
            }
            if (isExpanded) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Hedeflenen Soru Sayısı")
                    Text(text = questionGoalModel.goalQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Çözülen Soru Sayısı")
                    Text(text = questionGoalModel.solvedQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Doğru Sayısı")
                    Text(text = questionGoalModel.rightQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Yanlış Sayısı")
                    Text(text = questionGoalModel.falseQuestionQuantity.toString())
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Boş Sayısı")
                    Text(text = (questionGoalModel.goalQuestionQuantity - (questionGoalModel.rightQuestionQuantity + questionGoalModel.falseQuestionQuantity)).toString())
                }
            }
            if (isExpanded) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Arrow Down",
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow Down",
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}
