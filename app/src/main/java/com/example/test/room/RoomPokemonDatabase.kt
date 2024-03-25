package com.example.test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse


@Database(entities = [ResultsResponse::class,PokemonInfoResult::class], version = 3, exportSchema = true)
abstract class RoomPokemonDatabase : RoomDatabase() {

    abstract fun roomPokemonDao() : RoomPokemonDao

    }
