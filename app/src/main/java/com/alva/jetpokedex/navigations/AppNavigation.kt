package com.alva.jetpokedex.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alva.jetpokedex.screens.pokemon_info_screen.PokemonInfoScreen
import com.alva.jetpokedex.screens.pokemon_list_screen.PokemonListScreen
import java.util.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.PokeListScreen.name) {
        composable(Routes.PokeListScreen.name) {
            PokemonListScreen(navController = navController)
        }
        composable(
            Routes.PokeDetailScreen.name + "/{dominantColor}/{pokemonName}",
            arguments = listOf(navArgument("dominantColor") {
                type = NavType.IntType
            }, navArgument("pokemonName") {
                type = NavType.StringType
            })
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                val name = it.arguments?.getString("pokemonName")
                name ?: ""
            }
            PokemonInfoScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName.lowercase(Locale.ROOT),
                navController = navController
            )
        }
    }
}