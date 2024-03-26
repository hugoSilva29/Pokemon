package com.example.test.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse

@Dao
interface RoomPokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRoomPokemonItem(res: ResultsResponse)



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRoomPokemonInfoItem(res: PokemonInfoResult)
    @Query("SELECT * FROM PokemonInfo WHERE name = :name")
    suspend  fun getPokemonInfo(name: String): PokemonInfoResult

    @Query("SELECT * FROM PokemonList")
    suspend  fun getAll(): List<ResultsResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritePokemon(res: ResultsResponse)

    @Query("SELECT EXISTS(SELECT * FROM PokemonList WHERE `offset` = :id)")
    suspend fun isPokemonSaved(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM PokemonList WHERE `offset` = :id AND isFavorite = true)")
    suspend fun isPokemonFavorite(id: Int): Boolean
    @Query("DELETE FROM PokemonList WHERE `name` = :name")
    suspend fun deletePokemonFromRoom(name: String)

    @Query("DELETE FROM PokemonList")
    suspend fun deleteAllPokemonsFromRoom()

    @Query("SELECT * FROM PokemonList WHERE isFavorite = true")
    suspend fun getFavoritePokemons(): List<ResultsResponse>


}