package com.example.melitest.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.melitest.core.utils.Utils
import com.example.melitest.data.model.SearchModel
import com.example.melitest.data.network.SearchService
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
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


@get:Rule
var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    private lateinit var searchRepository: SearchRepository

    @Mock
    lateinit var api: SearchService


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
    fun `when get search receive empty result`() = runTest {
        val site = "MLA"
        val query = "billeteras"

        Mockito.`when`(api.searchProducts(site, query)).thenReturn(SearchModel())

        searchRepository = SearchRepository(api)
        val result = searchRepository.searchProducts(site, query)

        Assert.assertEquals(true, result.results.isNullOrEmpty())
    }

    @Test
    fun `when get search receive data result`() = runTest {
        val site = "MLA"
        val query = "billeteras"

        val quoteSearchModel = Utils.getMockkForTest(ApplicationProvider.getApplicationContext())

        Mockito.`when`(api.searchProducts(site, query)).thenReturn(quoteSearchModel)

        searchRepository = SearchRepository(api)
        val result = searchRepository.searchProducts(site, query)

        Assert.assertEquals(false, result.results.isNullOrEmpty())
        Assert.assertEquals(quoteSearchModel.results!![0].title, result.results!![0].title)
    }

}