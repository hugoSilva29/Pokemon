package com.example.test.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.test.MyApp
import com.example.test.R
import com.example.test.adapters.pokemonAbilityAdapter
import com.example.test.adapters.pokemonTypeAdapter
import com.example.test.databinding.FragmentDetailPokemonBinding
import com.example.test.pokemonlist.Abilitiyes
import com.example.test.pokemonlist.PokemonInfoResult
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.pokemonlist.Types
import com.example.test.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InfoPokemonFragment : Fragment() {

    private var _binding: FragmentDetailPokemonBinding? = null
    private var typeAdapter: pokemonTypeAdapter? = null
    private var abilityAdapter: pokemonAbilityAdapter? = null

    @Inject
    lateinit var repo: Repository

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        (context.applicationContext as MyApp).appComponent.injetPokemonInfo(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailPokemonBinding.inflate(inflater, container, false)

        val name = arguments?.getString("namePoke")

        lifecycleScope.launch {
            val l = repo.getPokemonInfo(name!!)
            val b= repo.isPokemonFavorite(l.id)
            if (b){
                setFavoriteIcon(binding.pokemonFav)
            }else{
                removeFavoriteIcon(binding.pokemonFav)
            }
            setData(l)
            l.types?.let { setUpRecyclerViewTypes(it) }
            setUpRecyclerViewability(l.abilities)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonFav.setOnClickListener {

                if (binding.pokemonFav.tag != resources.getString(R.string.favorite_tag)) {
                    val name = arguments?.getString("namePoke")
                    val resul = ResultsResponse(
                        binding.pokeId.text.toString().toInt(),
                        name!!,
                        binding.pokeId.text.toString(),
                        true
                    )
                    lifecycleScope.launch {
                        repo.addFavoritePokemon(resul)
                    }
                    Toast.makeText(context, "Pokemon added to favorites", Toast.LENGTH_SHORT).show()
                    setFavoriteIcon(binding.pokemonFav)
                }
                else{
                    val name = arguments?.getString("namePoke")
                    lifecycleScope.launch {
                       repo.deletePokemonFromRoom(name!!)
                    }
                    Toast.makeText(context, "Pokemon removed to favorites", Toast.LENGTH_SHORT).show()
                    removeFavoriteIcon(binding.pokemonFav)
                }

        }
    }

    private fun setData(l: PokemonInfoResult) {
        lifecycleScope.launch {
            binding.pokeId.text = l.id.toString()
            binding.pokemonName.text = l.name
            binding.pokeInfoHeight.text = l.height.toString()
            binding.pokeInfoWeight.text = l.weight.toString()

            context?.let {
                Glide.with(it)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + l.id.toString() + ".png")
                    .into(binding.pokemonInfoImage)
            }
            if (repo.isPokemonFavorite(l.id)) {
                setFavoriteIcon(binding.pokemonFav)
            }

        }
    }

    private fun setFavoriteIcon(imageView: ImageView) {
        imageView.setImageResource(R.drawable.baseline_favorite_24)
        imageView.tag = getString(R.string.favorite_tag)
    }

    private fun removeFavoriteIcon(imageView: ImageView) {
        imageView.setImageResource(R.drawable.favorite_border)
        imageView.tag = getString(R.string.not_favorite_tag)
    }


    private fun setUpRecyclerViewTypes(types: List<Types>) {
        typeAdapter = pokemonTypeAdapter()
        _binding?.pokemonRecyclerviewTypes?.adapter = typeAdapter
        _binding?.pokemonRecyclerviewTypes?.setHasFixedSize(true)
        _binding?.pokemonRecyclerviewTypes?.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(requireContext(), 1)
        typeAdapter?.setPokemonList(types)
    }

    private fun setUpRecyclerViewability(abilitiyes: List<Abilitiyes>?) {
        abilityAdapter = pokemonAbilityAdapter()
        _binding?.pokemonRecyclerviewAbilities?.adapter = abilityAdapter
        _binding?.pokemonRecyclerviewAbilities?.setHasFixedSize(true)
        _binding?.pokemonRecyclerviewAbilities?.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(requireContext(), 1)
        abilityAdapter?.setPokemonList(abilitiyes)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}