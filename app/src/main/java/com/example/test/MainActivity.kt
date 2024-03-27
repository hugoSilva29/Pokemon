package com.example.test

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.repository.Repository
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDatabase
import com.example.test.schedulers.SchedulersBase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var pokemonService: PokemonService
    @Inject lateinit var schedulers: SchedulersBase
    @Inject lateinit var repository: Repository
    @Inject lateinit var roomPokemonDatabase: RoomPokemonDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.favoritesFragment -> {
                // Navigate to FragmentX
                navController.navigate(R.id.favoritesPokemonFragment)
                true
            }
            R.id.ListFragment -> {
                // Navigate to FragmentX
                navController.navigate(R.id.FirstFragment)
                true
            }
            R.id.searchFragment -> {
                // Navigate to FragmentX
                navController.navigate(R.id.fragment_search)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}