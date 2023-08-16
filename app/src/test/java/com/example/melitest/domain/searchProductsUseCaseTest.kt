package com.example.melitest.domain

import androidx.test.core.app.ApplicationProvider
import com.example.melitest.core.utils.Utils
import com.example.melitest.data.SearchRepository
import com.example.melitest.data.model.SearchModel
import com.example.melitest.ui.viewmodel.SearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class SearchProductsUseCaseTest {

    @Mock
    private lateinit var searchRepository: SearchRepository

    private lateinit var searchProductsUseCase: SearchProductsUseCase

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
    fun `api return SearchModel valid`() = runBlocking {

        val site = "MLA"
        val query = "billeteras"
        // Given

        val quoteSearchModel = Utils.getMockkForTest(ApplicationProvider.getApplicationContext())

        Mockito.`when`(searchRepository.searchProducts(site, query))
            .thenReturn(quoteSearchModel)


        searchProductsUseCase = SearchProductsUseCase(searchRepository)

        // When
        val result = searchProductsUseCase.searchProduct(site, query)

        //Then
        Assert.assertEquals(quoteSearchModel.results!![0].title, result!!.results!![0].title)

    }

    @Test
    fun `api return SearchModel error`() = runBlocking {

        val site = "MLA"
        val query = "billeteras"
        // Given

        Mockito.`when`(searchRepository.searchProducts(site, query))
            .thenReturn(SearchModel())


        searchProductsUseCase = SearchProductsUseCase(searchRepository)

        // When
        val result = searchProductsUseCase.searchProduct(site, query)

        //Then
        Assert.assertEquals(true, result!!.results.isNullOrEmpty())

    }

}