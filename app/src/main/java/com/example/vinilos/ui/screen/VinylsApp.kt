package com.example.vinilos.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vinilos.ui.enumIU.VinylsScreen

@Composable
@Preview
fun VinylApp(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = VinylsScreen.Login.name
    ) {
        composable(route = VinylsScreen.Login.name) {
            Login(
                navigateTo = {
                    navController.navigate(it)
                }
            )
        }

        composable(route = VinylsScreen.HomeVisitant.name) {
            HomeVisitant(navigateTo = {navController.navigate(it)}, navigateUp = {navController.navigateUp()})
        }

        composable(route = VinylsScreen.HomeCollector.name) {
            HomeCollector(navigateTo = {navController.navigate(it)}, navigateUp = {navController.navigateUp()})
        }
    }

}
