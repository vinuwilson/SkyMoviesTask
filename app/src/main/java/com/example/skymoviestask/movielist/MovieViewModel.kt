package com.example.skymoviestask.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val movieList = liveData<Result<MovieList>> {
        loader.postValue(true)
        emitSource(repository.getMovieList().onEach {
            loader.postValue(false)
        }.asLiveData())

    }
}
