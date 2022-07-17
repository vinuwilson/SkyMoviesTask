package com.example.skymoviestask.movielist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: MovieService
) {
    suspend fun getMovieList(): Flow<Result<MovieList>> =
        service.fetchMovieList()


}
