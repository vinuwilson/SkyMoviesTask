package com.example.skymoviestask

import com.example.skymoviestask.movielist.MovieList
import com.example.skymoviestask.movielist.MovieRepository
import com.example.skymoviestask.movielist.MovieViewModel
import com.example.skymoviestask.utils.BaseUnitTest
import com.example.skymoviestask.utils.captureValues
import com.example.skymoviestask.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.verify

class MovieViewModelShould :BaseUnitTest() {

    private val repository: MovieRepository = mock()
    private val movieList = mock<MovieList>()
    private val expected = Result.success(movieList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getMovieListFromRepository(): Unit = runBlocking{

        val viewModel = mockSuccessfulCase()

        viewModel.movieList.getValueForTest()

        verify(repository, times(1)).getMovieList()
    }

    @Test
    fun emitMovieListFromRepository() = runBlocking {
        val viewModel = mockSuccessfulCase()

        viewModel.movieList.getValueForTest()

        assertEquals(expected, viewModel.movieList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlocking {
        val viewModel = mockFailureCase()

        assertEquals(exception, viewModel.movieList.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runBlocking {

        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.movieList.getValueForTest()

            assertEquals(true, values[0])
        }
    }

   @Test
   fun hideSpinnerAfterLoading() = runBlocking {

       val viewModel = mockSuccessfulCase()

       viewModel.loader.captureValues {
           viewModel.movieList.getValueForTest()

           assertEquals(false, values.last())
       }
   }

    @Test
    fun hideSpinnerAfterError() = runBlocking {

        val viewModel = mockFailureCase()

        viewModel.loader.captureValues {
            viewModel.movieList.getValueForTest()

            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): MovieViewModel {
        runBlocking {
            whenever(repository.getMovieList()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return MovieViewModel(repository)
    }

    private suspend fun mockFailureCase(): MovieViewModel {
        whenever(repository.getMovieList()).thenReturn(
            flow {
                emit(Result.failure<MovieList>(exception))
            }
        )
        return MovieViewModel(repository)
    }
}