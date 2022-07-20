package com.example.skymoviestask.movielist.network

import com.example.skymoviestask.movielist.db.MovieDao
import com.example.skymoviestask.movielist.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao
) {
//    suspend fun getMovieList(): Flow<Result<MovieList>> =
//        service.fetchMovieList()

    private suspend fun insertRecord(movie: List<Movie>) {
        withContext(Dispatchers.IO) {
            movieDao.deleteAllRecords()
            movieDao.insertRecord(movie)
        }
    }

    suspend fun getMovieList(): Flow<List<Movie>> {
        return flow {
            if (service.fetchMovieList().first().isFailure) {
                emit(movieDao.getAllRecords())
            } else {
                emit(service.fetchMovieList().first().getOrNull()!!.data)
                insertRecord(service.fetchMovieList().first().getOrNull()!!.data)
            }
        }
    }

}
