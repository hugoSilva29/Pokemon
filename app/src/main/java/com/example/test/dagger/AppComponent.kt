package com.example.test.dagger

import com.example.test.MainActivity
import com.example.test.fragments.InfoPokemonFragment
import com.example.test.fragments.ListPokemonFragment
import com.example.test.fragments.favoritesPokemonFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SchedulerModule::class, PokemonServiceModule::class, RetrofitModule::class, ContextModule::class, RepositoryModule::class,RoomModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun injectPokemonList(pokemonListFragment: ListPokemonFragment)
    fun injetPokemonInfo(pokemonInfoFragment: InfoPokemonFragment)
    fun injectPokemonFavorites(favoritesPokemonFragment: favoritesPokemonFragment)
}