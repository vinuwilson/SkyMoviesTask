package com.example.skymoviestask.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skymoviestask.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var viewModelFactory: MovieViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://movies-sample.herokuapp.com/api/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(MovieListAPI::class.java)
    private val service = MovieService(api)
    private val repository = MovieRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        setupViewModel()

        viewModel.movieList.observe(this as LifecycleOwner) { movieList ->
            if (movieList.getOrNull() != null) {
                setupList(view, movieList.getOrNull()!!)
            }
        }
        return view
    }

    private fun setupViewModel() {
        viewModelFactory = MovieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
    }

    private fun setupList(
        view: View?,
//        movieList: List<Data>
        movieList: MovieList
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)

//            adapter = MyItemRecyclerViewAdapter(movieList)
            adapter = MyItemRecyclerViewAdapter(movieList.data)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieFragment().apply {
            }
    }
}