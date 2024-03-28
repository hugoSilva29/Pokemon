package com.example.test.room



import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.pokemonlist.Ability
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RoomPokemonDaoTest {
    private lateinit var db: RoomPokemonDatabase
    private lateinit var dao: RoomPokemonDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RoomPokemonDatabase::class.java).build()
        dao = db.roomPokemonDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun addRoomPokemonItem_insertsPokemon() = runTest {
        val pokemon = ResultsResponse(1, "bulbasaur", "1",true)

        dao.addRoomPokemonItem(pokemon)

        val allPokemons = dao.getAll()
        assertTrue(allPokemons.any { it.offset == pokemon.offset })
    }

    @Test
    fun getPokemonInfo_returnsExpectedResult() = runTest {
        val pokemonInfo = PokemonInfoResult().apply {
            name = "bulbasaur"

        }
        dao.addRoomPokemonInfoItem(pokemonInfo)

        val retrievedPokemonInfo = dao.getPokemonInfo("bulbasaur")

        assertEquals(pokemonInfo.name, retrievedPokemonInfo.name)
    }
    @Test
    fun isPokemonFavorite_returnsExpectedResult() = runTest {
        val pokemon = ResultsResponse(1, "bulbasaur", "1",true)
        dao.addRoomPokemonItem(pokemon)

        val isFavorite = dao.isPokemonFavorite(1)

        assertTrue(isFavorite)
    }

    @Test
    fun deletePokemonFromRoom_deletesPokemon() = runTest {
        val pokemon =ResultsResponse(1, "bulbasaur", "1",true)
        dao.addRoomPokemonItem(pokemon)

        dao.deletePokemonFromRoom("bulbasaur")
        val allPokemons = dao.getAll()

        assertFalse(allPokemons.contains(pokemon))
    }

    @Test
    fun deleteAllPokemonsFromRoom_deletesAllPokemons() = runTest {
        val pokemon1 = ResultsResponse(1, "bulbasaur", "1")
        dao.addRoomPokemonItem(pokemon1)

        dao.deleteAllPokemonsFromRoom()
        val allPokemons = dao.getAll()

        assertTrue(allPokemons.isEmpty())
    }
}