package com.example.test.repository

import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse

interface RepositoryInterface {
    suspend fun getPokemon(id: Int): ResultsResponse
    suspend fun getPokemonList(): ListPokemon
    suspend fun getPokemonInfo(name: String): PokemonInfoResult
    suspend fun addPokemonToRoom(pokemon: ResultsResponse)
    suspend fun getAllPokemonsFromRoom(): List<ResultsResponse>
    suspend fun isPokemonFavorite(id: Int): Boolean
    suspend fun deletePokemonFromRoom(name: String)
    suspend fun deleteAllPokemonsFromRoom()
    suspend fun getPokemons(offset: Int,limit:Int): ListPokemon
    suspend fun getFavoritePokemons(): List<ResultsResponse>
}