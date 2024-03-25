package com.example.test.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDao
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class Repository @Inject constructor(
    private val pokemonService: PokemonService,
    private val roomPokemonDao: RoomPokemonDao,
    private val context: Context
) :
    RepositoryInterface {
    override suspend fun getPokemon(id: Int): ResultsResponse {
        return pokemonService.getPokemon(id)
    }

    private val pokemonList: MutableList<ResultsResponse> = ArrayList()
    private val compositeDisposable = CompositeDisposable()
    private val pokemonListLiveData = MutableLiveData<List<ResultsResponse>>()
    private val progressBarLiveData = MutableLiveData<Boolean>()
    private val swipeRefreshLayoutLiveData = MutableLiveData<Boolean>()
    private val toastLiveData = MutableLiveData<String>()

    override suspend fun getPokemonList(): ListPokemon {
        return withContext(Dispatchers.IO) {
            pokemonService.get151Pokemons()
        }
    }
    override suspend fun getPokemons(offset: Int,limit:Int): ListPokemon {
       try {
            val pokemons = pokemonService.getPokemons(offset, limit)
            pokemons.results?.forEach { pokemon ->
                addPokemonToRoom(pokemon)
            }
            return pokemons
        } catch (e: Exception) {
            // Handle the exception (e.g., log the error)
            if (e is IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
            // Fetch the Pokemons from the Room database instead
            val pokemonsFromRoom = roomPokemonDao.getAll()
            return ListPokemon(pokemonsFromRoom)
        }
    }

    override suspend fun getFavoritePokemons(): List<ResultsResponse> {
        return withContext(Dispatchers.IO) {
            roomPokemonDao.getFavoritePokemons()
        }

    }

    override suspend fun getPokemonInfo(name: String): PokemonInfoResult {
        return try {
            val pokemons =pokemonService.getPokemon(name)
            roomPokemonDao.addRoomPokemonInfoItem(pokemons)
            pokemons
        } catch (e: Exception) {
            // Handle the exception (e.g., log the error)
            if (e is IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
                }
            }
            // Fetch the Pokemons from the Room database instead
            roomPokemonDao.getPokemonInfo(name)
        }
    }

    override suspend fun addPokemonToRoom(pokemon: ResultsResponse) {
            roomPokemonDao.addRoomPokemonItem(pokemon)
    }

    override suspend fun getAllPokemonsFromRoom(): List<ResultsResponse> {
        return withContext(Dispatchers.IO) {
            roomPokemonDao.getAll()
        }
    }

    override suspend fun isPokemonFavorite(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            roomPokemonDao.isPokemonFavorite(id)
        }
    }

    override suspend fun deletePokemonFromRoom(name: String) {
        roomPokemonDao.deletePokemonFromRoom(name)
    }

    override suspend fun deleteAllPokemonsFromRoom() {
        roomPokemonDao.deleteAllPokemonsFromRoom()
    }




}