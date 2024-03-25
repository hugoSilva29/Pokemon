package com.example.test.pokemonlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.test.util.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PokemonInfo")
@TypeConverters(Converters::class)
class PokemonInfoResult {
    var id: Int = 0
    @field:PrimaryKey
    @field:SerializedName("name")
    lateinit var name: String
    var height: Int = 0
    var weight: Int = 0
    var types: List<Types>? = null
    var abilities: List<Abilitiyes>? = null
}