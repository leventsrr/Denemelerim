package com.leventsurer.denemelerim.presentation.home_screen.views.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.leventsurer.denemelerim.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(appBarTitle:String) {
    TopAppBar(
        title =
        {
            Text(appBarTitle, textAlign = TextAlign.Center)
        },
        actions = {

            IconButton(onClick = { /*TODO*/ }) {

                Icon(painter = painterResource(id = R.drawable.baseline_filter_list_24), contentDescription = "asdasd")
            }
            IconButton(onClick = { /*TODO*/ }) {

                Icon(painter = painterResource(id = R.drawable.baseline_bar_chart_24), contentDescription = "asdasd")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "sdgdfg")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "")
            }
        }
    )
}