package com.example.test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.RowLayoutBinding
import com.example.test.pokemonlist.ResultsResponse
import java.util.ArrayList

class pokemonListAdapter(private val context: Context,

) : RecyclerView.Adapter<pokemonListAdapter.RecyclerViewViewHolder>() {
    private val pokemonList: ArrayList<ResultsResponse> = ArrayList()
    private var binding: RowLayoutBinding? = null
     var listener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onClick(namePoke: String?, imagePoke: String?)
    }



    fun setPokemonList(pokemonList1: MutableList<ResultsResponse>) {
        val uniquePokemonList = pokemonList1.toSet().toMutableList()
        pokemonList.clear()
        pokemonList.addAll(uniquePokemonList)
        notifyDataSetChanged()
    }

    fun addPokemons(pokemonList1: List<ResultsResponse>) {
        val newPokemons = pokemonList1.filter { it !in pokemonList }
        pokemonList.addAll(newPokemons)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        binding?.tvPokemonName?.text = pokemon.name
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.url+ ".png")
            .into(binding?.ivPokemonImage!!)
        holder.bind(pokemon)
        holder.itemView.setOnClickListener {
            listener?.onClick(pokemon.name,pokemon.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        binding= RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      //  listener= context as OnItemClickListener
        return RecyclerViewViewHolder(binding!! )
    }
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class RecyclerViewViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: ResultsResponse) {
            binding.tvPokemonName.text = pokemon.name
            Glide.with(binding.root)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.url+ ".png")
                .into(binding.ivPokemonImage)
        }
    }
}