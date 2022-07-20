package com.example.skymoviestask.movielist.viwmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.skymoviestask.movielist.model.Movie
import com.example.skymoviestask.movielist.network.MovieRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val movieList = liveData<List<Movie>> {
        loader.postValue(true)
        emitSource(repository.getMovieList().onEach {
            loader.postValue(false)
        }.asLiveData())

    }
}
