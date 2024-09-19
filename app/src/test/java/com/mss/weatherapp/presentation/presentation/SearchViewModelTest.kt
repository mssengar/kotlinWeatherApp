package com.mss.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.Observer
import com.mss.weatherapp.domain.models.SearchResultModel
import com.mss.weatherapp.presentation.screen.search.SearchViewModel
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest : BaseViewModelTest() {
    lateinit var searchViewModel: SearchViewModel

    @Mock
    lateinit var searchStringObserver: Observer<String>

    @Mock
    lateinit var autocompleteResultObserver: Observer<List<SearchResultModel>>

    @Mock
    lateinit var errorMessageObserver: Observer<String>

    @Mock
    lateinit var isClearSearchIconVisibleObserver: Observer<Boolean>

    @Captor
    lateinit var searchResultCaptor: ArgumentCaptor<List<SearchResultModel>>

    override fun setUp() {
        searchViewModel = spyk(SearchViewModel(searchCityUseCase, getDeviceRegionUseCase))

        searchViewModel.searchString.observeForever(searchStringObserver)
        searchViewModel.autocompleteResult.observeForever(autocompleteResultObserver)
        searchViewModel.errorMessage.observeForever(errorMessageObserver)
        searchViewModel.isClearSearchIconVisible.observeForever(isClearSearchIconVisibleObserver)
    }

    @Test
    suspend fun `performSearch is Success`() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) }.returns(0)
        val response = searchCityUseCase.invoke("searchString")

        runTest {

            searchResultCaptor.run {
                Mockito.verify(autocompleteResultObserver, times(2)).onChanged(capture())
            }

            Assert.assertTrue(searchResultCaptor.allValues[0].isEmpty())
            Assert.assertTrue(searchResultCaptor.allValues[1].size == response.toArray().size)
        }
    }

    @Test
    fun `onSearchFieldValueCleared is successful`() {
        searchViewModel.searchString.value?.let { Assert.assertTrue(it.isEmpty()) }
    }

    @Test
    fun `onSearchFieldValueCleared is unsuccessful`() {
        searchViewModel.searchString.value?.let { Assert.assertTrue(it.isNotEmpty()) }
    }

    @Test
    suspend fun `onSearchString change is successful`() {
        if (searchViewModel.searchString.value?.length ?: 0 > 2) {
            `performSearch is Success`()
        }
    }
}