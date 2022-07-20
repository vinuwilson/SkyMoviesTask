package com.example.skymoviestask.movielist.viwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skymoviestask.movielist.network.MovieRepository
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(
    private val repository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}
