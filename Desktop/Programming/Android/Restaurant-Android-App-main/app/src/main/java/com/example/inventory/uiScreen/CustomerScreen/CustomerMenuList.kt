package com.example.restaurantapp.uiScreen.CustomerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.data.Menu
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerMenuList(
    modifier: Modifier,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
) {

    val uiState by customerViewModel.uiState.collectAsState()
    var tableNumber = uiState.currentTableNumber
    val ownerId = uiState.currentOwnerId
    val menuList = uiState.currentMenuList

    Scaffold(
        topBar = {
            RestaurantAppBar(tableNumber)
        },
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked = onBillClicked,
                onViewOrderClicked = onViewOrderClicked,
                onCallwaiterClicked = onCallwaiterClicked,
                onMenuClicked = onMenuClicked,
                "View Order",
                Icons.Filled.ShoppingCart,
                viewModel = customerViewModel
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 0.dp
                )
            ) {
                Text(
                    text = "Our Menu",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(5.dp)
                )
                CategoryTab(
                    modifier = Modifier,
                    customerViewModel = customerViewModel,
                    databaseViewModel = databaseViewModel,
                    ownerId
                )
                Menu(
                    menuList,
                    customerViewModel = customerViewModel
                )

            }

        }
    }
}


@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
    ownerId:Int
) {
    val radioOptions = listOf("All", "Appetizer", "MainDish", "Dessert", "Drink")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    if (selectedOption == "All") {
        val menus by databaseViewModel.menus(ownerId).collectAsState(emptyList())
        customerViewModel.setCurrentMenuList(menus)
    } else {
        val menus by databaseViewModel.getMenuByCategory(ownerId, selectedOption)
            .collectAsState(emptyList())
        customerViewModel.setCurrentMenuList(menus)
    }

    Row(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Column(modifier = Modifier.padding(end = 3.dp))
            {
                FilterChip(
                    onClick = { onOptionSelected(text) },
                    label = { Text(text) },
                    selected = (text == selectedOption),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        labelColor = Color.Black,
                        selectedContainerColor = Color.Black,
                        selectedLabelColor = Color.White
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = text == selectedOption,
                        borderColor = Color.Black,
                        selectedBorderColor = Color.Black,
                        disabledBorderColor = Color.LightGray
                    )
                )
            }
        }
    }
//    Text(selectedOption)

}

@Composable
fun Menu(menus: List<Menu>, customerViewModel: CustomerViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
    ) {
        items(menus) { menu ->
            MenuItem(menu, customerViewModel)
        }
    }
}


@Composable
fun MenuItem(menu: Menu, customerViewModel: CustomerViewModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
//            .aspectRatio(1.5f)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {


            AsyncImage(
                model = menu.image,
                contentDescription = "Sushi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp).padding(vertical = 5.dp),
//                placeholder = painterResource(R.drawable.ic_placeholder), // optional
                error = painterResource(R.drawable.ic_broken_image)       // optional
            )

            Text(
                text = menu.name,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(text=menu.description,
                maxLines = 2)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${menu.price}$",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier)
                IconButton(
                    onClick = { customerViewModel.setOrderList(menu.menuId, 1) },
                    colors = IconButtonColors(
                        MaterialTheme.colorScheme.onSecondaryContainer,
                        MaterialTheme.colorScheme.onPrimary,
                        Color.Black,
                        Color.Black
                    )
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
    }
}










