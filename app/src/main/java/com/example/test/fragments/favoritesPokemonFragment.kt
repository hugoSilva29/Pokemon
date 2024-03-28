package com.example.test.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.MyApp
import com.example.test.R
import com.example.test.adapters.pokemonListAdapter
import com.example.test.databinding.FragmentFavoritsPokemonBinding
import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class favoritesPokemonFragment : Fragment(), pokemonListAdapter.OnItemClickListener {

    private var _binding: FragmentFavoritsPokemonBinding? = null
    private var listAdapter: pokemonListAdapter? = null
    private var list: MutableList<ResultsResponse> = mutableListOf()

    @Inject
    lateinit var repo: Repository

    override fun onAttach(context: Context) {
        (context.applicationContext as MyApp).appComponent.injectPokemonFavorites(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritsPokemonBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            val l = repo.getFavoritePokemons()
            getFavorites(ListPokemon(l))
            setUpRecyclerView()
        }
        return _binding!!.root
    }


    private fun getFavorites(l: ListPokemon) {
        for (i in 0 until l.results!!.size) {
            val name: String = l.results[i].name
            var url: String = l.results[i].url
            val id: Int = url.toInt()
            if (list.any { it.offset == id }) {
                continue  // Skip this iteration if id is already present
            }
            val resul = ResultsResponse(id, name, url)
            list.add(resul)
        }
    }

    private fun setUpRecyclerView() {
        listAdapter = pokemonListAdapter(requireActivity())
        _binding?.pokemonRecyclerview?.adapter = listAdapter
        _binding?.pokemonRecyclerview?.setHasFixedSize(true)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape mode, you might want more columns
            _binding?.pokemonRecyclerview?.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(requireContext(), 5)
        } else {
            // In portrait mode
            _binding?.pokemonRecyclerview?.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
        }
        listAdapter?.setPokemonList(list)
        listAdapter?.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(namePoke: String?, imagePoke: String?) {
        val bundle = Bundle().apply {
            putString("namePoke", namePoke)
            putString("imagePoke", imagePoke)
        }
        findNavController().navigate(R.id.action_favoritesPokemonFragment_to_SecondFragment, bundle)
    }
}