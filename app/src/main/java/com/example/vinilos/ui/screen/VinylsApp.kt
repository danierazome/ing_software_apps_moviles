package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinilos.ui.component.BottomBar
import com.example.vinilos.ui.enumIU.BottomBarOption
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
fun VinylsApp(navController: NavHostController = rememberNavController()) {

    var bottomBarItemSelected by rememberSaveable { mutableStateOf(BottomBarOption.Home.name) }

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedItem = bottomBarItemSelected,
                onSelect = {
                    bottomBarItemSelected = it
                    navController.navigate(it)
                }
                )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = VinylsScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = VinylsScreen.Login.name) {
                Login()
            }
            composable(route = VinylsScreen.HomeVisitant.name) {
                HomeVisitant()
            }
            composable(route = VinylsScreen.Albums.name) {
                Albums()
            }
            composable(route = VinylsScreen.Collectors.name) {
                Collectors()
            }
            composable(route = VinylsScreen.Artists.name) {
                Artists()
            }

        }

    }
}