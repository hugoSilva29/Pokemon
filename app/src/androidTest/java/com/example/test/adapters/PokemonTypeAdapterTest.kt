package com.example.test.adapters
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import com.example.test.R

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PokemonTypeAdapterTest {

    private lateinit var adapter: pokemonTypeAdapter

    @Before
    fun setUp() {
        adapter = pokemonTypeAdapter()
    }

    @Test
    fun getItemCount_returnsCorrectSize() {
        val types = listOf(Types(1, Type("Type1", "URL1")), Types(2, Type("Type2", "URL2")))
        adapter.setPokemonList(types)

        val itemCount = adapter.itemCount

        assertEquals(2, itemCount)
    }

    @Test
    fun onCreateViewHolder_createsViewHolder() {
        val parent = FrameLayout(ApplicationProvider.getApplicationContext())
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        assertNotNull(viewHolder)
        assertTrue(viewHolder is pokemonTypeAdapter.RecyclerViewViewHolder)
    }

    @Test
    fun onBindViewHolder_setsCorrectData() {
        val types = listOf(Types(1, Type("Type1", "URL1")), Types(2, Type("Type2", "URL2")))
        adapter.setPokemonList(types)

        val parent = FrameLayout(ApplicationProvider.getApplicationContext())
        val viewHolder = adapter.onCreateViewHolder(parent, 0)
        adapter.onBindViewHolder(viewHolder, 0)


    }
}
