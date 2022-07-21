package com.alva.jetpokedex.apis

import com.alva.jetpokedex.remote_models.Pokemon
import com.alva.jetpokedex.remote_models.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): Pokemon
}