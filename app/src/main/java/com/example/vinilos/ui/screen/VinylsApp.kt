package com.example.vinilos.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import com.example.vinilos.ui.component.BottomBarCollector
import com.example.vinilos.ui.component.BottomBarVisitor
import com.example.vinilos.ui.enumIU.CollectorsScreen
import com.example.vinilos.ui.enumIU.UserType
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
@Preview
fun VinylApp(navController: NavHostController = rememberNavController()) {
    var bottomBarItemSelected by rememberSaveable { mutableStateOf(VinylsScreen.Home.name) }
    var bottomBarItemSelectedCollector by rememberSaveable { mutableStateOf(CollectorsScreen.Home.name) }
    var userType by rememberSaveable { mutableStateOf(UserType.None.name) }

    Scaffold(
        bottomBar = {
            if (userType != UserType.None.name) {
                when (userType) {
                    UserType.Visitor.name ->
                        BottomBarVisitor(
                            selectedItem = bottomBarItemSelected,
                            onSelect = {
                                bottomBarItemSelected = it;
                                navController.navigate(it)
                            }
                        )
                    UserType.Collector.name ->
                        BottomBarCollector(
                            selectedItem = bottomBarItemSelectedCollector,
                            onSelect = {
                                bottomBarItemSelectedCollector = it;
                                navController.navigate(it)
                            }
                        )
                }
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
                if(userType.equals(UserType.Visitor.name)) {
                    HomeVisitant()
                }
                else
                    HomeCollector()
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

            composable(route = CollectorsScreen.Login.name) {
                Login(
                    updateUser = {
                        userType = it
                    },
                    navigateTo = {
                        navController.navigate(it)
                    }
                )
            }
            composable(route = CollectorsScreen.New.name) {
                NewArtist()
            }
            composable(route = CollectorsScreen.Albums.name) {
                Albums()
            }
            composable(route = CollectorsScreen.Artists.name) {
                Artists()
            }


        }
    }

}
