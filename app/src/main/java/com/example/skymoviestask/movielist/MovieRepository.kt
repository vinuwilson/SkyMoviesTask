package com.example.skymoviestask.movielist

import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val service: MovieService
) {
    suspend fun getMovieList(): Flow<Result<MovieList>> =
        service.fetchMovieList()


}
