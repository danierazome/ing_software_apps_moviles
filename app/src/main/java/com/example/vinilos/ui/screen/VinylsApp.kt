package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinilos.ui.component.BottomBar
import com.example.vinilos.ui.enumIU.UserType
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
@Preview
fun VinylApp(navController: NavHostController = rememberNavController()) {
    var bottomBarItemSelected by rememberSaveable { mutableStateOf(VinylsScreen.Home.name) }
    var userType by rememberSaveable { mutableStateOf(UserType.None.name) }

    Scaffold(
        bottomBar = {
            if (userType != UserType.None.name) {
                BottomBar(
                    selectedItem = bottomBarItemSelected,
                    onSelect = {
                        bottomBarItemSelected = it
                        navController.navigate(it)
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = VinylsScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = VinylsScreen.Login.name) {
                Login(
                    updateUser = {
                        userType = it
                    },
                    navigateTo = {
                        navController.navigate(it)
                    }
                )
            }
            composable(route = VinylsScreen.Home.name) {
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