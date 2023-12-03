package com.example.vinilos.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        navController.popBackStack()

        composable(route = VinylsScreen.HomeCollector.name) {
            HomeCollector(navigateTo = {navController.navigate(it)}, navigateUp = {navController.navigateUp()})
        }

        composable(
            "${VinylsScreen.Album.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val id = arguments.getInt("id")
            DetailedAlbum(id= id, navigateUp = {navController.navigateUp()})
        }

        composable(
            "${VinylsScreen.Artist.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val id = arguments.getInt("id")
            DetailedArtist(id= id, navigateUp = {navController.navigateUp()})
        }

    }

}
