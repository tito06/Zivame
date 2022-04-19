package com.example.zivproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.zivproject.data.Cart
import com.example.zivproject.data.Product
import com.example.zivproject.navigation.Routes
import com.example.zivproject.ui.theme.ZivProjectTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.Home.routes){

        composable(Routes.Home.routes){
            HomePage(navController = navController)
        }

        composable(Routes.Cart.routes){
            MyCart(navController = navController)
        }
    }
}






@Composable
fun HomePage(navController: NavController) {

 val homePageViewModel:HomePageViewModel = viewModel()

 val scope = rememberCoroutineScope()
 val scaffoldState = rememberScaffoldState()

 Scaffold(
     scaffoldState = scaffoldState,
     drawerShape = CustomShape(),
     modifier = Modifier
         .fillMaxWidth()
         .fillMaxHeight(),
     backgroundColor = Color.Transparent,
     topBar = {
         TopAppBar(title = {
             Text(text = "Home",
             color = Color.Black)
         },
         navigationIcon = {
             IconButton(onClick = {
                 scope.launch {
                     if (scaffoldState.drawerState.isClosed)
                         scaffoldState.drawerState.open()
                     else
                         scaffoldState.drawerState.close()
                 }
             }) {
                 Icon(painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                     contentDescription ="" )

             }
         })
     },
     drawerElevation = 4.dp,
     drawerGesturesEnabled = true,
     drawerContent = {

         NavDrawer(navController = navController)
     }

 ) {
     Column(modifier = Modifier
         .fillMaxHeight()
         .fillMaxWidth()) {

         ItemCard(itemList = homePageViewModel.items)

         homePageViewModel.getItems()

     }

 }





}


@Composable
fun CustomShape() = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return  Outline.Rectangle(Rect(0f,0f,600f,7000f))
    }

}


@Composable
fun ItemCard(itemList:List<Product>){


    val context = LocalContext.current

    val homePageViewModel:HomePageViewModel = viewModel()

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start){


        items(itemList){ itemList ->


            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(4.dp, 4.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(6.dp)) {

                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start){

                    Image(painter = rememberAsyncImagePainter(itemList.image_url),
                        contentDescription = "",
                        modifier = Modifier.width(100.dp),
                        contentScale = ContentScale.FillHeight)

                    Column() {

                        Text(text = itemList.name)
                        Spacer(modifier = Modifier.height(2.dp))

                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)) {

                            Image(painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                                contentDescription = "",)
                            Text(text = "${itemList.rating}")

                        }

                        Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {

                            Text(text = itemList.price)
                            Image(painter = painterResource(id = R.drawable.ic_baseline_add_circle_24),
                                contentDescription = "",
                            modifier = Modifier.clickable {
                                homePageViewModel.selectedItems.add(Cart(itemList.image_url,itemList.name,itemList.price))
                                Log.d("My Cart", homePageViewModel.selectedItems.toString())
                                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                            })

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(3.dp))


        }

    }



}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ZivProjectTheme {
     //   ItemCard()
    }
}