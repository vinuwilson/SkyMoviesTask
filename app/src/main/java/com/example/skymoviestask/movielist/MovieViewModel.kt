package com.example.skymoviestask.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movieList = liveData<Result<MovieList>> {
        emitSource(repository.getMovieList().asLiveData())
    }
}
