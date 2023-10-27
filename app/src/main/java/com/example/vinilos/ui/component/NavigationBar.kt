package com.example.vinilos.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vinilos.ui.enumIU.BottomBarOption

@Composable
fun BottomBar(selectedItem: String, modifier: Modifier = Modifier, onSelect: (String) -> Unit = {}) {

    NavigationBar {
        BottomBarOption.values().forEach { item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == item.name,
                onClick = {onSelect(item.name)}
            )
        }
    }
}