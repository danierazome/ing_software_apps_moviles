package com.example.vinilos.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vinilos.ui.enumIU.CollectorsScreen
import com.example.vinilos.ui.enumIU.UserType
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
fun BottomBarVisitor(selectedItem: String, modifier: Modifier = Modifier, onSelect: (String) -> Unit = {}) {

    NavigationBar {
        VinylsScreen.values().filter { it.inBar }.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == item.name,
                onClick = { onSelect(item.name) }
            )
        }
    }
}
@Preview
@Composable
fun PreviewBottomBarVisitor(){
    BottomBarVisitor("Home")
}

@Composable
fun BottomBarCollector(selectedItem: String, modifier: Modifier = Modifier, onSelect: (String) -> Unit = {}) {

    NavigationBar {
        CollectorsScreen.values().filter { it.inBar }.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = selectedItem == item.name,
                onClick = { onSelect(item.name) }
            )
        }
    }
}
@Preview
@Composable
fun PreviewBottomBarCollector(){
    BottomBarCollector("Home")
}