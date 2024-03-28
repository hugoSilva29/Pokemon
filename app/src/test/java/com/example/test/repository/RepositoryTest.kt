package com.example.test.repository

import android.content.Context
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    private lateinit var mockPokemonService: PokemonService

    @Mock
    private lateinit var mockRoomPokemonDao: RoomPokemonDao

    @Mock
    private lateinit var mockContext: Context

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(mockPokemonService, mockRoomPokemonDao, mockContext)
    }

    @Test
    fun getPokemon_returnsExpectedResult() = runTest {
        val expectedPokemon = ResultsResponse(1, "bulbasaur", "1")
        Mockito.`when`(mockPokemonService.getPokemon(1)).thenReturn(expectedPokemon)

        val actualPokemon = repository.getPokemon(1)

        assertEquals(expectedPokemon, actualPokemon)
    }
    @Test
    fun getPokemonInfo_returnsExpectedResult() = runTest {
        val expectedPokemonInfo = PokemonInfoResult().apply {
            name = "bulbasaur"
        }
        Mockito.`when`(mockPokemonService.getPokemon("bulbasaur")).thenReturn(expectedPokemonInfo)

        val actualPokemonInfo = repository.getPokemonInfo("bulbasaur")

        assertEquals(expectedPokemonInfo, actualPokemonInfo)
    }

    @Test
    fun addPokemonToRoom_insertsPokemon() = runTest {
        val pokemon = ResultsResponse(1, "bulbasaur", "1",true)

        repository.addPokemonToRoom(pokemon)

        verify(mockRoomPokemonDao).addRoomPokemonItem(pokemon)
    }

    @Test
    fun deletePokemonFromRoom_deletesPokemon() = runTest {
        repository.deletePokemonFromRoom("bulbasaur")

        verify(mockRoomPokemonDao).deletePokemonFromRoom("bulbasaur")
    }

    @Test
    fun deleteAllPokemonsFromRoom_deletesAllPokemons() = runTest {
        repository.deleteAllPokemonsFromRoom()

        verify(mockRoomPokemonDao).deleteAllPokemonsFromRoom()
    }

}