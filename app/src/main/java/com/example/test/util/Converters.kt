package com.example.test.util

import androidx.room.TypeConverter
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Types
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTypesList(types: List<Types>?): String {
        return gson.toJson(types)
    }

    @TypeConverter
    fun toTypesList(typesString: String): List<Types>? {
        val type = object : TypeToken<List<Types>>() {}.type
        return gson.fromJson(typesString, type)
    }

    @TypeConverter
    fun fromAbilitiesList(abilities: List<Abilitiyes>?): String {
        return gson.toJson(abilities)
    }

    @TypeConverter
    fun toAbilitiesList(abilitiesString: String): List<Abilitiyes>? {
        val type = object : TypeToken<List<Abilitiyes>>() {}.type
        return gson.fromJson(abilitiesString, type)
    }
}
