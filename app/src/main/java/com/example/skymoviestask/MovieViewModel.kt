package com.example.skymoviestask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel :ViewModel(){

//    val movieList = MutableLiveData<Result<List<Data>>>()
    val movieList = MutableLiveData<Result<MovieList>>()
}
