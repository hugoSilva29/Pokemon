package com.example.test.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.AbilityLayoutBinding
import com.example.test.databinding.TypeLayoutBinding
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Ability
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types
import okhttp3.internal.notify

class pokemonAbilityAdapter() :
    RecyclerView.Adapter<pokemonAbilityAdapter.RecyclerViewViewHolder>() {

    private val pokemonList: ArrayList<Abilitiyes> = ArrayList()
    private var binding: AbilityLayoutBinding? = null

    fun setPokemonList(pokemonList1: List<Abilitiyes>?) {
        if (pokemonList1 != null) {
            pokemonList.addAll(pokemonList1)
        }
        if (this.binding != null) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolder {
        binding = AbilityLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewViewHolder(binding!!)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewViewHolder,
        position: Int
    ) {
        val pokemon = pokemonList[position]
        binding?.pokeInfoTypeOne?.text = pokemon.ability.name
        holder.bind(pokemon.ability)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


    open class RecyclerViewViewHolder(private val binding: AbilityLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Ability) {
            binding.pokeInfoTypeOne.text = pokemon.name

        }
    }
}