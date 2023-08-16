package com.example.melitest.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.melitest.core.utils.Utils.getMockkForTest
import com.example.melitest.domain.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @Mock
    private lateinit var searchProductsUseCase: SearchProductsUseCase

    private lateinit var searchViewModel: SearchViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel  make a  search and fail it`() = runTest {

        val site = "MLA"
        val query = "billeteras"
        // Given

        Mockito.`when`(searchProductsUseCase.searchProduct(site, query))
            .thenReturn(null)


        searchViewModel = SearchViewModel(searchProductsUseCase)

        // When
        searchViewModel.searchProducts(site, query)
        val result = searchViewModel.searchModel

        //Then
        Assert.assertEquals(false, result.isInitialized)

    }

    @Test
    fun `when viewmodel  make a  search`() = runTest {

        val site = "MLA"
        val query = "billeteras"
        // Given

        val quoteSearchModel = getMockkForTest(ApplicationProvider.getApplicationContext())

        Mockito.`when`(searchProductsUseCase.searchProduct(site, query))
            .thenReturn(quoteSearchModel)


        searchViewModel = SearchViewModel(searchProductsUseCase)

        // When
        searchViewModel.searchProducts(site, query)
        val result = searchViewModel.searchModel

        //Then
        Assert.assertEquals(quoteSearchModel.results!![0].title, result.value!!.results!![0].title)

    }


}