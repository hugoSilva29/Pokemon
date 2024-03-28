package com.example.test.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.adapters.pokemonListAdapter
import com.example.test.databinding.FrgamentSearchBinding
import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.ResultsResponse
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), pokemonListAdapter.OnItemClickListener {

    private var _binding: FrgamentSearchBinding? = null
    private var listAdapter: pokemonListAdapter? = null
    private var list: MutableList<ResultsResponse> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrgamentSearchBinding.inflate(inflater, container, false)


        return _binding!!.root
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
        findNavController().navigate(R.id.action_fragment_search_to_SecondFragment, bundle)
    }


}