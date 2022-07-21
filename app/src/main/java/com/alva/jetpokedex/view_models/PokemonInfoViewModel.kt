package com.alva.jetpokedex.view_models

import androidx.lifecycle.ViewModel
import com.alva.jetpokedex.remote_models.Pokemon
import com.alva.jetpokedex.repos.PokeRepo
import com.alva.jetpokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(private val repository: PokeRepo) : ViewModel() {
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemon(pokemonName)
    }
}