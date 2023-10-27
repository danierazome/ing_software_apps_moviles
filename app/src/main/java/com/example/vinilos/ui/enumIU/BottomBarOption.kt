package com.example.vinilos.ui.enumIU

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBarOption(val icon: ImageVector) {
    Home(Icons.Filled.Home),
    Albums(Icons.Filled.LibraryMusic),
    Collectors(Icons.Filled.Groups),
    Artists(Icons.Filled.Person)
}