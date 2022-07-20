package com.example.skymoviestask.movielist.network

import com.example.skymoviestask.movielist.model.MovieList
import com.example.skymoviestask.movielist.network.MovieListAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieListAPI
) {
    suspend fun fetchMovieList(): Flow<Result<MovieList>> {

        return flow {
            emit(Result.success(api.fetchAllMovieList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
