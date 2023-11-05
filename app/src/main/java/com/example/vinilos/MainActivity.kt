package com.example.vinilos

import android.app.FragmentManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.BackEventCompat
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.vinilos.ui.screen.VinylApp
import com.example.vinilos.ui.theme.VinilosTheme


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        val callback = object : OnBackPressedCallback(enabled=true) {

            override fun handleOnBackPressed() {
                Toast.makeText(this@MainActivity, "Cerrando la app", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        }
        super.onCreate(savedInstanceState)
        //OnBackPressedDispatcher.
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VinylApp()
                }
            }
        }
        this.onBackPressedDispatcher.addCallback( callback)

    }


}



