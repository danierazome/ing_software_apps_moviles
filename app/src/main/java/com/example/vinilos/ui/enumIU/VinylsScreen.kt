package com.example.vinilos.ui.enumIU

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector



enum class VinylsScreen(val icon: ImageVector, val inBar: Boolean, val userType: UserType, val label: String) {
    Login(Icons.Filled.Login, false, UserType.None, "Login"),
    HomeVisitant(Icons.Filled.Home, true, UserType.Visitor, "Home"),
    AlbumsVisitant(Icons.Filled.LibraryMusic, true, UserType.Visitor, "Albums"),
    CollectorsVisitant(Icons.Filled.Groups, true, UserType.Visitor, "Collectors"),
    ArtistsVisitant(Icons.Filled.Person, true, UserType.Visitor, "Artists"),
    HomeCollector(Icons.Filled.Home, true, UserType.Collector, "Home"),
    AlbumsCollector(Icons.Filled.LibraryMusic, true, UserType.Collector, "Albums"),
    ArtistsCollector(Icons.Filled.Person, true, UserType.Collector, "Artists"),
    New(Icons.Filled.Add, true, UserType.Collector, "New")
}