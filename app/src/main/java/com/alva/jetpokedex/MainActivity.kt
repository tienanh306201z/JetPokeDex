package com.alva.jetpokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.alva.jetpokedex.navigations.AppNavigation
import com.alva.jetpokedex.utils.JetPokeDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AppNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit = {}) {
    JetPokeDexTheme {
        content.invoke()
    }
}