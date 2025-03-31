package com.example.restaurantapp.uiScreen

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.R
import com.example.restaurantapp.data.UiState
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerMenuList(
    modifier: Modifier,
    viewModel: ViewModel = ViewModel(),
    onViewOrderClicked: () -> Unit,
    onBillClicked: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    var tableNumber = uiState.tableNumber
    val orderList = uiState.orderList

    Scaffold(
        topBar = {
            RestaurantAppBar()
        },
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked,
                onViewOrderClicked,
                "View Order",
                Icons.Filled.ShoppingCart
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Table: $tableNumber")
                CategoryTab()
                Menu()
            }

        }
    }
}


@Composable
fun CategoryTab(modifier: Modifier = Modifier) {
    val radioOptions = listOf("All", "Appetizer", "Main dish", "Dessert", "Drink")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Row(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Column(modifier = Modifier.padding(end = 3.dp))
            {
                FilterChip(
                    onClick = { onOptionSelected(text) },
                    label = { Text(text) },
                    selected = (text == selectedOption)
                )
            }
        }
    }
    Text("You select $selectedOption")
}

@Composable
fun Menu() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
    ) {
        items(5) { index ->
            MenuItem(index)
        }
    }
}

@Composable
fun MenuItem(index: Int) {
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
            Image(
                painter = painterResource(R.drawable.ic_broken_image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Menu name $index",
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Price$$", modifier = Modifier)
                IconButton(onClick = {},
                    colors = IconButtonColors(Color.White, MaterialTheme.colorScheme.primaryContainer, Color.Black,Color.Black)
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








