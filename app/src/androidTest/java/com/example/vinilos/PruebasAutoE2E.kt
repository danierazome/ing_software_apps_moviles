package com.example.vinilos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.vinilos.ui.screen.VinylApp
import com.example.vinilos.ui.theme.VinilosTheme
import okhttp3.internal.wait

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
    fun Escenario01() {
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
    fun Escenario02() {
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
    fun Escenario03() {
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
    fun Escenario04() {
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
    fun Escenario05() {
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
    fun Escenario05() {
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
        composeTestRule.onNodeWithText("elisa").performClick()

    }

    @Test
    fun Escenario06() {
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
        composeTestRule.onNodeWithText("elisa").performClick()
    }


}