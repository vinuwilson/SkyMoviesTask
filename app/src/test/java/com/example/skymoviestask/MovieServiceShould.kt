package com.example.skymoviestask

import com.example.skymoviestask.movielist.model.MovieList
import com.example.skymoviestask.movielist.network.MovieListAPI
import com.example.skymoviestask.movielist.network.MovieService
import com.example.skymoviestask.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieServiceShould :BaseUnitTest() {

    private lateinit var service: MovieService
    private val api: MovieListAPI = mock()
    private val movieLists : MovieList = mock()

    @Test
    fun fetchMovieListFromAPI(): Unit = runBlocking {

        service = MovieService(api)

        service.fetchMovieList().first()

        verify(api, times(1)).fetchAllMovieList()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem(): Unit = runBlocking {

        mockSuccessfulCase()

        assertEquals(Result.success(movieLists), service.fetchMovieList().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlocking {

        mockFailureCase()

        assertEquals("Something went wrong", service.fetchMovieList().first().exceptionOrNull()?.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllMovieList()).thenReturn(movieLists)

        service = MovieService(api)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllMovieList()).thenThrow(RuntimeException("Damn backend developer"))

        service = MovieService(api)
    }

}

