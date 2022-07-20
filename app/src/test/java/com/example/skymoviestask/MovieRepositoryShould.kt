package com.example.skymoviestask

import com.example.skymoviestask.movielist.db.MovieDao
import com.example.skymoviestask.movielist.model.MovieList
import com.example.skymoviestask.movielist.network.MovieRepository
import com.example.skymoviestask.movielist.network.MovieService
import com.example.skymoviestask.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieRepositoryShould : BaseUnitTest() {

    private val service : MovieService = mock()
    private val movieList = mock<MovieList>()
    private val exception = RuntimeException("Something went wrong")
    private val movieDao: MovieDao = mock()

    @Test
    fun getMovieListFromService(): Unit = runBlocking {

        val repository = MovieRepository(service, movieDao)

        repository.getMovieList()

        verify(service, times(1)).fetchMovieList()
    }

    @Test
    fun emitMovieListFromService(): Unit = runBlocking {

        val repository = mockSuccessfulCase()

        assertEquals(movieList, repository.getMovieList().first())
    }

    @Test
    fun emitError() = runBlocking {

        val repository = mockFailureCase()

        assertEquals(exception, repository.getMovieList().first())
    }

    private suspend fun mockSuccessfulCase(): MovieRepository {
        whenever(service.fetchMovieList()).thenReturn(
            flow {
                emit(Result.success(movieList))
            }
        )

        return MovieRepository(service, movieDao)
    }

    private suspend fun mockFailureCase(): MovieRepository {
        whenever(service.fetchMovieList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        return MovieRepository(service, movieDao)
    }
}