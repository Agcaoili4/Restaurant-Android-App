package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.data.Menu
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.restaurantapp.uiScreen.CustomerScreen.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuListScreen(
    modifier: Modifier = Modifier,
    onMenuAdded: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {},
    databaseViewModel: DatabaseViewModel,
) {

    val menus by databaseViewModel.menus(1).collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Menu Management") }
            )
        },
        bottomBar = {
            OwnerBottomAppBar(
                selectedIndex = 1,
                onIncomingClicked = onIncomingClicked,
                onManageClicked = onManageClicked,
                onHistoryClicked = onHistoryClicked,
                onSettingClicked = onSettingClicked
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Button(
                onClick =onMenuAdded ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Add new")
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(menus) { menu ->
                    MenuItem(menu)
                }
            }
        }
    }
}


@Composable
fun MenuItem(menu: Menu) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Row {
                AsyncImage(
                    model = menu.image,
                    contentDescription = "Sushi",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(120.dp)
                        .padding(end = 8.dp),
                    error = painterResource(R.drawable.ic_broken_image)       // optional
                )
                Column {
                    Text(
                        text = menu.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Text(text = menu.description, maxLines = 2)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${menu.price}$",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                        IconButton(
                            onClick = {/* Go to update screen */ },
                            colors = IconButtonColors(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer,
                                Color.Black,
                                Color.Black
                            )
                        ) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}


