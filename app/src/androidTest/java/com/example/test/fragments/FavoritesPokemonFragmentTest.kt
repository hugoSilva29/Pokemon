package com.example.test.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import com.example.test.R
import com.example.test.repository.Repository
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FavoritesPokemonFragmentTest {

    private lateinit var fragment: favoritesPokemonFragment
    private lateinit var mockNavController: NavController
    private lateinit var scenario: FragmentScenario<favoritesPokemonFragment>

    @Before
    fun setUp() {
        val mockPokemonService = Mockito.mock(PokemonService::class.java)
        val  mockRoomPokemonDao = Mockito.mock(RoomPokemonDao::class.java)
        val  mockContext = Mockito.mock(Context::class.java)
        val  repository = Repository(mockPokemonService, mockRoomPokemonDao, mockContext)
        mockNavController = Mockito.mock(NavController::class.java)
        fragment = favoritesPokemonFragment().also { fragment ->
            fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                fragment.repo = repository
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(fragment.requireView(), mockNavController)
                }
            }
        }
        scenario = launchFragmentInContainer(themeResId = R.style.Base_Theme_Test)
    }

    @Test
    fun testNavigationToSecondFragment() {
        val expectedPokemonName = "bulbasaur"
        val expectedPokemonImage = "1"

        scenario.onFragment { fragment ->
            fragment.onClick(expectedPokemonName, expectedPokemonImage)
        }

        val expectedBundle = Bundle().apply {
            putString("namePoke", expectedPokemonName)
            putString("imagePoke", expectedPokemonImage)
        }

        verify(mockNavController).navigate(R.id.action_favoritesPokemonFragment_to_SecondFragment, expectedBundle)
    }

    // Add more tests as needed...
}
