package com.example.vinilos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import com.example.vinilos.ui.screen.VinylApp
import com.example.vinilos.ui.theme.VinilosTheme

import org.junit.Test

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class E2ETests {
    @get:Rule
    val composeTestRule = createComposeRule()
    //val composeTestRule = createAndroidComposeRule<MainActivity>()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun scenario01() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()
    }

    @Test
    fun scenario02() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN COLECCIONISTA").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Coleccionista!").assertIsDisplayed()
    }

    @Test
    fun scenario03() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("GO BACK BUTTON").performClick()
        composeTestRule.onNodeWithText("SOY UN COLECCIONISTA").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Coleccionista!").assertIsDisplayed()
    }

    @Test
    fun scenario04() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("GO BACK BUTTON").performClick()
        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()

    }
    @Test
    fun scenario05() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Artists").performClick()
        composeTestRule.onNodeWithText("Mis Artistas").assertIsDisplayed()
    }

    @Test
    fun scenario06() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN VISITANTE").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Visitante!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText("elisa").assertIsDisplayed()

    }

    @Test
    fun scenario07() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN COLECCIONISTA").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Coleccionista!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Albums").performClick()
        composeTestRule.onNodeWithText("elisa").assertIsDisplayed()
    }

    @Test
    fun scenario08() {
        // Start the app
        composeTestRule.setContent {
            VinilosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }

        composeTestRule.onNodeWithText("SOY UN COLECCIONISTA").performClick()
        composeTestRule.onNodeWithText("¡Bienvenido Coleccionista!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Prize").performClick()
        composeTestRule.onNodeWithText("Nombre").performTextInput("Premio 01")
        composeTestRule.onNodeWithText("Descripción").performTextInput("Alguna descripción")
        composeTestRule.onNodeWithText("Organización").performTextInput("organizacion 01")

    }


}