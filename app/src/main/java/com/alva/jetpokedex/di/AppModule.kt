package com.alva.jetpokedex.di

import com.alva.jetpokedex.apis.PokeApi
import com.alva.jetpokedex.repos.PokeRepo
import com.alva.jetpokedex.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokemonRepository(api: PokeApi) = PokeRepo(api)

    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(PokeApi::class.java)
    }
}