package com.leventsurer.denemelerim.presentation.set_target_screen.views.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.leventsurer.denemelerim.presentation.home_screen.views.composable.TytExamCard
import com.leventsurer.denemelerim.presentation.ui.theme.Primary

@Composable
fun CustomSpinnerDialog(
    showDialog: Boolean,
    options: List<String>,
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = "Seçim Yapın")
            },
            text = {
                LazyColumn(

                ) {
                    items(options) { item ->
                        Button(
                            colors = ButtonDefaults.buttonColors(Primary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            onClick = {
                                onItemClick(item)
                                onDismiss()
                            }
                        ) {
                            Text(text = item)
                        }
                    }
                }

            },
            confirmButton = {
                // You can add a confirm button if needed
            },
            dismissButton = {
                // You can add a dismiss button if needed
            }
        )
    }
}