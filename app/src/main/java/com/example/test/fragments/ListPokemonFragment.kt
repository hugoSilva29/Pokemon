package com.example.test.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.adapters.pokemonListAdapter
import com.example.test.databinding.FragmentListPokemonBinding
import com.example.test.pokemonlist.ListPokemon
import com.example.test.pokemonlist.ResultsResponse
import com.example.test.MyApp
import com.example.test.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListPokemonFragment : Fragment(),
    pokemonListAdapter.OnItemClickListener {
    // In your ListPokemonFragment class
    private var page = 1
    private val limit = 20

    @Inject
    lateinit var repo: Repository

    private var _binding: FragmentListPokemonBinding? = null
    private var listAdapter: pokemonListAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var list: MutableList<ResultsResponse> = mutableListOf()
    override fun onAttach(context: Context) {
        (context.applicationContext as MyApp).appComponent.injectPokemonList(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPokemonBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            //val l = repo.getPokemonList()
           // addPokemons(l)
           // setUpRecyclerView()
            loadPokemons()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun loadPokemons() {
        lifecycleScope.launch {
            val offset =( page-1) * limit
            val l = repo.getPokemons( limit,offset)
            addPokemons(l)
            setUpRecyclerView()
            page++  // Increment the page number for the next load
        }
    }

    private fun addPokemons(l: ListPokemon) {
        for (i in 0 until l.results!!.size) {
            val name: String = l.results[i].name
            var url: String
            if (l.results[i].url.length > 33) {
                url  = l.results[i].url.replaceFirst(".$", "").substring(33)
                url = url.substring(1, url.length - 1)
            }
            else{
                 url= l.results[i].url
            }
            val id: Int = url.toInt()
            if (list.any { it.offset == id }) {
                continue  // Skip this iteration if id is already present
            }
            val resul = ResultsResponse(id, name, url)
            list.add(resul)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {

        _binding?.pokemonRecyclerview?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == list.size - 1) {
                    loadPokemons()

                }
            }
        })
       // if (listAdapter == null) {
            listAdapter = pokemonListAdapter(requireActivity())
            _binding?.pokemonRecyclerview?.adapter = listAdapter
            _binding?.pokemonRecyclerview?.setHasFixedSize(true)
            _binding?.pokemonRecyclerview?.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
            listAdapter?.setPokemonList(list)
            listAdapter?.listener = this
      //  } else {
        //    listAdapter?.addPokemons(list)
        //}
    }

    override fun onClick(namePoke: String?, imagePoke: String?) {
        val bundle = Bundle().apply {
            putString("namePoke", namePoke)
            putString("imagePoke", imagePoke)
        }
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

}