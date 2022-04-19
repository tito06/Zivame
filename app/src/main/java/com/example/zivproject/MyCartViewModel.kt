package com.example.zivproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.zivproject.data.Cart

class MyCartViewModel:ViewModel() {

   // val cartItem by mutableStateOf(listOf<Cart>())

    val cartItem = mutableStateListOf<Cart>()


}