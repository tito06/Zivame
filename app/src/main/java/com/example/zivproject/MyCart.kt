package com.example.zivproject

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.zivproject.data.Cart
import com.example.zivproject.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var showDialoge =
    mutableStateOf(false)


@SuppressLint("UnrememberedMutableState")
@Composable
fun MyCart(navController: NavController){





    val scope = rememberCoroutineScope()
    val circularProgress = CircularProgressIndicator()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "My Cart",
                    color = Color.White)
            })

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    showDialoge.value = true
                    delay(5000)
                    showDialoge.value = false
                    navController.navigate(Routes.Home.routes)

                }
                //delay(5000)
            }) {

                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_circle_24),
                    contentDescription ="" )

            }

        }
    ) {


        val cartViewModel:MyCartViewModel = viewModel()



        val cartItem = mutableStateListOf<Cart>()


        CartItem(item = cartItem)




    }






}




@Composable
fun CartItem(item:List<Cart>){

    if (showDialoge.value){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {

            CircularProgressIndicator()


        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){

        items(item){ item ->
         
            itemCard(imageurl = item.image_url,
                name = item.name ,
                price = item.price)
            

        }


    }
}


@Composable
fun itemCard(imageurl:String, name:String , price:String){



    Card(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)) {

        Image(painter = rememberAsyncImagePainter(imageurl),
            contentDescription ="" )
        Text(text = name)
        Text(text = price)

    }


}