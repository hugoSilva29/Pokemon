package com.example.test.adapters

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.test.databinding.RowLayoutBinding
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Ability
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockedConstruction
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.example.test.R

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class pokemonListAdapterTest {

    private lateinit var adapter: pokemonListAdapter

    @Before
    fun setUp() {
        adapter = pokemonListAdapter(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getItemCount_returnsCorrectSize() {
        val pokemons = mutableListOf(ResultsResponse(1, "Pokemon1", "URL1"), ResultsResponse(2, "Pokemon2", "URL2"))
        adapter.setPokemonList(pokemons)

        val itemCount = adapter.itemCount

        assertEquals(2, itemCount)
    }

    @Test
    fun onCreateViewHolder_createsViewHolder() {
        val parent = FrameLayout(ApplicationProvider.getApplicationContext())
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        assertNotNull(viewHolder)
        assertTrue(viewHolder is pokemonListAdapter.RecyclerViewViewHolder)
    }

    @Test
    fun onBindViewHolder_setsCorrectData() {
        val pokemons = mutableListOf(ResultsResponse(1, "Pokemon1", "1"), ResultsResponse(2, "Pokemon2", "2"))
        adapter.setPokemonList(pokemons)

        val parent = FrameLayout(ApplicationProvider.getApplicationContext())
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        runOnUiThread {
            adapter.onBindViewHolder(viewHolder, 0)
        }
      //  adapter.onBindViewHolder(viewHolder, 0)

        // Here you can assert that the correct data was set on the view holder
        // For example, if you have a TextView for the pokemon name, you can do:
         assertEquals("Pokemon1", viewHolder.itemView.findViewById<TextView>(R.id.tv_pokemon_name).text)
    }
}
