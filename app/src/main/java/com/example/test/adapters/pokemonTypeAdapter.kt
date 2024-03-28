package com.example.test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.TypeLayoutBinding
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types

class pokemonTypeAdapter : RecyclerView.Adapter<pokemonTypeAdapter.RecyclerViewViewHolder>() {
    private val pokemonList: MutableList<Types> = mutableListOf()
    private var binding: TypeLayoutBinding? = null

    fun setPokemonList(pokemonList1: List<Types>) {

        pokemonList.addAll(pokemonList1)

        if (this.binding != null) {
            notifyDataSetChanged()
        }    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolder {
        binding = TypeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerViewViewHolder,
        position: Int
    ) {
        val pokemon = pokemonList[position]
        handlePokemonType(pokemon.type.name)
        binding?.ivPokemonImage
        holder.bind(pokemon.type)

    }

    private fun handlePokemonType(type: String) {
        when (type) {
            "normal" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_normal)
            "fighting" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_fighter)
            "flying" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_voador)
            "poison" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_poisan)
            "ground" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ground)
            "rock" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_rpck)
            "bug" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_bug)
            "ghost" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ghost)
            "steel" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_steel)
            "fire" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_fire)
            "water" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_water)
            "grass" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_grass)
            "electric" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_eletric)
            "psychic" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_psychc)
            "ice" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ice)
            "dragon" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_dragon)
            "dark" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_dark)
            "fairy" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_fairy)
            "unknown" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_unkown)
            else -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_unkown)
        }
    }


  open  class RecyclerViewViewHolder(val binding: TypeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Type) {
            handlePokemonType(pokemon.name)
        }


        private fun handlePokemonType(type: String) {
            when (type) {
                "normal" -> binding.ivPokemonImage.setImageResource(R.mipmap.type_normal)
                "fighting" -> binding.ivPokemonImage?.setImageResource(R.mipmap.type_fighter)
                "flying" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_voador)
                "poison" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_poisan)
                "ground" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ground)
                "rock" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_rpck)
                "bug" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_bug)
                "ghost" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ghost)
                "steel" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_steel)
                "fire" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_fire)
                "water" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_water)
                "grass" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_grass)
                "electric" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_eletric)
                "psychic" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_psychc)
                "ice" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_ice)
                "dragon" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_dragon)
                "dark" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_dark)
                "fairy" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_fairy)
                "unknown" -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_unkown)
                else -> binding?.ivPokemonImage?.setImageResource(R.mipmap.type_unkown)
            }
        }
    }

}