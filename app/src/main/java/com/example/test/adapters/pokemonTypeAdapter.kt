package com.example.test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.TypeLayoutBinding
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.Type
import com.example.test.pokemonlist.Types

class pokemonTypeAdapter: RecyclerView.Adapter<pokemonTypeAdapter.RecyclerViewViewHolder>() {
    private val pokemonList: MutableList<Types> = mutableListOf()
    private var binding: TypeLayoutBinding? = null

 fun setPokemonList(pokemonList1: List<Types>) {

         pokemonList.addAll(pokemonList1)

        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewViewHolder {
        binding= TypeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        binding?.pokeInfoTypeOne?.text = pokemon.type.name
        holder.bind(pokemon.type)

    }

    class RecyclerViewViewHolder(private val binding: TypeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Type) {
            binding.pokeInfoTypeOne.text = pokemon.name

        }
    }

}