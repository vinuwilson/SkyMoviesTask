package com.example.skymoviestask.movielist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository {
    suspend fun getMovieList(): Flow<Result<MovieList>> {
        return flow {  }
    }

}
