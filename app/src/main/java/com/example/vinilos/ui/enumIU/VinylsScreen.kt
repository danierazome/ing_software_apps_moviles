package com.example.vinilos.ui.enumIU

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector



enum class VinylsScreen(val icon: ImageVector, val inBar: Boolean) {
    Login(Icons.Filled.Login, false),
    Home(Icons.Filled.Home, true),
    Albums(Icons.Filled.LibraryMusic, true),
    Collectors(Icons.Filled.Groups, true),
    Artists(Icons.Filled.Person, true),
}

enum class CollectorsScreen(val icon: ImageVector, val inBar: Boolean) {
    Login(Icons.Filled.Login, false),
    Home(Icons.Filled.Home, true),
    Albums(Icons.Filled.LibraryMusic, true),
    Artists(Icons.Filled.Person, true),
    New(Icons.Filled.Add, true)
}