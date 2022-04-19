package com.example.zivproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.zivproject.data.Cart
import com.example.zivproject.data.MyResponse
import com.example.zivproject.data.Product
import com.example.zivproject.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel:ViewModel() {

    var items:List<Product> by mutableStateOf( listOf() )
    var selectedItems:MutableList<Cart> by mutableStateOf(mutableListOf())

    lateinit var apiInterface: ApiInterface

    fun getItems(){

        CoroutineScope(Dispatchers.IO).launch {
            apiInterface = ApiInterface.create()
            val response = apiInterface.getData()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    items = response.body()!!.products
                }
            }
        }
    }
}