package com.alva.jetpokedex.repos

import com.alva.jetpokedex.apis.PokeApi
import com.alva.jetpokedex.remote_models.Pokemon
import com.alva.jetpokedex.remote_models.PokemonList
import com.alva.jetpokedex.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokeRepo @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val result = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred!")
        }
        return Resource.Success(result)
    }

    suspend fun getPokemon(pokemonName: String): Resource<Pokemon> {
        val result = try {
            api.getPokemonInfo(name = pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred!")
        }
        return Resource.Success(result)
    }
}