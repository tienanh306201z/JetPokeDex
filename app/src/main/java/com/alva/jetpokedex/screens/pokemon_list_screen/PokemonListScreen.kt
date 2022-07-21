package com.alva.jetpokedex.screens.pokemon_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alva.jetpokedex.R
import com.alva.jetpokedex.screens.pokemon_list_screen.widgets.PokeDexEntry
import com.alva.jetpokedex.screens.pokemon_list_screen.widgets.PokeSearchBar
import com.alva.jetpokedex.view_models.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pokemon_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            PokeSearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchPokemonList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            PokeDexGrid(navController = navController)
        }
    }
}

@Composable
fun PokeDexGrid(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember {
        viewModel.pokemonList
    }
    val endReached by remember {
        viewModel.endReached
    }
    val loadError by remember {
        viewModel.loadError
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    val isSearching by remember {
        viewModel.isSearching
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(pokemonList.size) {
            if (it >= pokemonList.size - 1 && !endReached && !isLoading && !isSearching) {
                viewModel.loadPokemonPaginated()
            }
            PokeDexEntry(entry = pokemonList[it], navController = navController)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        } else if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }
}

@Composable
fun RetrySection(error: String, onRetryClick: () -> Unit) {
    Column(horizontalAlignment = CenterHorizontally) {
        Text(
            text = error,
            style = MaterialTheme.typography.titleMedium.copy(color = Color(0xffff493d))
        )
        Spacer(modifier = Modifier.height(8.dp))
        FilledTonalButton(onClick = { onRetryClick.invoke() }) {
            Text(text = "Retry")
        }
    }
}