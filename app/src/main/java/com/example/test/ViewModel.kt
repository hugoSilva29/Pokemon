package com.example.test

import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
/*
    private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
    private var job: Job = Job()
    val list: MutableList<ResultsResponse> = mutableListOf()
    private val _myPokemon: MutableLiveData<Result<MutableList<ResultsResponse>>> = MutableLiveData()
    val myPokemon: LiveData<Result<MutableList<ResultsResponse>>>
        get() = _myPokemon

    private val _myPokemonNamesList: MutableLiveData<Result<ListPokemon>> = MutableLiveData()
    val myPokemonNamesList: LiveData<Result<ListPokemon>>
        get() = _myPokemonNamesList
    private val pokemonService: PokemonService by lazy {
        RetrofitClient.pokemonService
    }
    init {
        coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            _myPokemon.value = Result.Failure(exception)
        }
        pokemonsDisplayed = 0
        getAllPokemonNames()
    }

      fun getAllPokemonNames() {
        cancelJobIfRunning()
        job = viewModelScope.launch(coroutineExceptionHandler) {
            _myPokemonNamesList.value = Result.Loading
            _myPokemonNamesList.value =
                Result.Success(RetrofitClient.pokemonService.get151Pokemons())
        }
    }
    private fun cancelJobIfRunning() {
        if (job.isActive) {
            job.cancel()
        }
    }
    sealed class Result<out T : Any> {
        data class Success<out T : Any>(val value: T) : Result<T>()
        data object Loading : Result<Nothing>()
        data class Failure(val throwable: Throwable) : Result<Nothing>()
    }

 */
}