package com.example.zivproject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.navigation.NavController
import com.example.zivproject.navigation.Routes

@Composable
fun NavDrawer(navController: NavController){

    Column {

        Text(text = "My Cart",
        modifier = Modifier.clickable {
            navController.navigate(Routes.Cart.routes)
        })
    }
}