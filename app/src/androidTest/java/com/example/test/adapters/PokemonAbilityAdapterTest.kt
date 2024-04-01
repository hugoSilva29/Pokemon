package com.example.test.adapters

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Ability
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.test.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PokemonAbilityAdapterTest {

    private lateinit var adapter: pokemonAbilityAdapter
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        adapter = pokemonAbilityAdapter()
    }

    @Test
    fun getItemCount_returnsCorrectSize() {
        val abilities = listOf(Abilitiyes(1, Ability("Ability1", "URL1")), Abilitiyes(2, Ability("Ability2", "URL2")))
        adapter.setPokemonList(abilities)

        val itemCount = adapter.itemCount

        assertEquals(2, itemCount)
    }

    @Test
    fun onCreateViewHolder_createsViewHolder() {
        val parent = FrameLayout(context)
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        assertNotNull(viewHolder)
        assertTrue(viewHolder is pokemonAbilityAdapter.RecyclerViewViewHolder)
    }

    @Test
    fun onBindViewHolder_setsCorrectData() {
        val abilities = listOf(Abilitiyes(1, Ability("Ability1", "URL1")), Abilitiyes(2, Ability("Ability2", "URL2")))
        adapter.setPokemonList(abilities)

        val parent = FrameLayout(context)
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)

        // Here you can assert that the correct data was set on the view holder
        // For example, if you have a TextView for the ability name, you can do:
        assertEquals("Ability1", viewHolder.itemView.findViewById<TextView>(R.id.poke_info_type_one).text)
    }
}

