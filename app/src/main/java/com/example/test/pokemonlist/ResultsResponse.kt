package com.example.test.pokemonlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "PokemonList")
class ResultsResponse(
    var offset: Int,
    @field:PrimaryKey @field:SerializedName("name") var name: String,
    @field:SerializedName(
        "url"
    ) var url: String,
    var isFavorite: Boolean = false
)
