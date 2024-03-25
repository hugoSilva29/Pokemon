package com.example.test.retorfit

import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonService {
    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): ResultsResponse

    @GET("pokemon")
    suspend fun getPokemons( @Query("limit") limit: Int,@Query("offset") offset: Int): ListPokemon

    @GET("pokemon?offset=0&limit=151")
    suspend fun get151Pokemons(): ListPokemon

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): PokemonInfoResult
}