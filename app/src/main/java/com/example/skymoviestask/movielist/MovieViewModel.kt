package com.example.skymoviestask.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    //    val movieList = MutableLiveData<Result<List<Data>>>()
    val movieList = liveData<Result<MovieList>> {
        emitSource(repository.getMovieList().asLiveData())
    }
}
