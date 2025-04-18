package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Menu
import com.example.inventory.R
import com.example.inventory.theme.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuListScreen(
    ownerId: Int,
    databaseViewModel: DatabaseViewModel,
    onMenuAdded: () -> Unit,
    onEditMenu: (Menu) -> Unit,
    onIncomingClicked: () -> Unit,
    onManageClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onSettingClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Observe the list of menus for the given owner.
    val menus by databaseViewModel.menus(ownerId).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Menu Management") })
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
        // Place the LazyColumn directly as Scaffold's content.
        LazyColumn(
            contentPadding = innerPadding,
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                AppButton(
                    onClick = onMenuAdded,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Add New")
                }
            }
            items(menus) { menu ->
                MenuItem(menu = menu, onEdit = { onEditMenu(menu) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    menu: Menu,
    onEdit: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .graphicsLayer {

                alpha = 0.85f
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = menu.image,
                contentDescription = menu.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .padding(end = 8.dp),
                error = painterResource(id = R.drawable.ic_broken_image)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = menu.name, fontWeight = FontWeight.Bold)
                Text(text = menu.description)
                Text(text = "${menu.price}$", fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = onEdit) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Menu"
                )
            }
        }
    }
}