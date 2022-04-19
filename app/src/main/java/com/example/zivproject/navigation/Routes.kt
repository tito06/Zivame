package com.example.zivproject.navigation

sealed class Routes(val routes:String){

    object Cart: Routes("cart")
    object Home: Routes("home")
}
