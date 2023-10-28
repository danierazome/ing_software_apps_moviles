package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vinilos.ui.enumIU.UserType
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
fun Login(modifier: Modifier = Modifier,updateUser: (String) -> Unit = {}, navigateTo: (String) -> Unit = {}) {
    Column {
        Button(
            onClick =
            {
                updateUser(UserType.Collector.name)
                navigateTo(VinylsScreen.Home.name)
            },
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(UserType.Collector.name)
        }
        Button(
            onClick =
            {
                updateUser(UserType.Visitor.name)
                navigateTo(VinylsScreen.Home.name)
            },
            modifier = modifier.widthIn(min = 250.dp)
        ) {
            Text(UserType.Visitor.name)
        }
    }
}
