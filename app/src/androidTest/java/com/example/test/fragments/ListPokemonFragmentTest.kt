package com.example.test.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.test.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDao

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ListPokemonFragmentTest {

    @Mock
    private lateinit var mockPokemonService: PokemonService

    @Mock
    private lateinit var mockRoomPokemonDao: RoomPokemonDao

    @Mock
    private lateinit var mockContext: Context

    // Create a mock instance of Repository
    @Mock
    private lateinit var repository: Repository


    // Create an instance of ListPokemonFragment
    private lateinit var listPokemonFragment: ListPokemonFragment
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = Repository(mockPokemonService, mockRoomPokemonDao, mockContext)

        val bundle = Bundle()
        /*
                scenario = launchFragmentInContainer<ListPokemonFragment>(
                    bundle,
                    R.style.Base_Theme_Test,
                    factory = object : FragmentFactory() {
                        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                            return listPokemonFragment
                        }
                    })

         */


    }

    @Test
    fun testOnDestroyView() {
        scenario = launchFragmentInContainer<SearchFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        // Test that onDestroyView correctly nullifies the binding
        //   listPokemonFragment.onDestroyView()
        //    assertNull(listPokemonFragment.binding)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testOnAttach() = runTest {
        // Test that onAttach correctly initializes the repo
        assertNotNull(listPokemonFragment.repo)
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun onViewCreated() {
    }

    @Test
    fun onDestroyView() {
    }

    @Test
    fun onClick() {
    }

    @Test
    fun onResume() {
    }
}