package com.example.skymoviestask.movielist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.skymoviestask.R
import com.example.skymoviestask.movielist.adapter.MyItemRecyclerViewAdapter
import com.example.skymoviestask.movielist.model.Movie
import com.example.skymoviestask.movielist.viwmodel.MovieViewModel
import com.example.skymoviestask.movielist.viwmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    private lateinit var adapter: MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        setupViewModel()

        observeLoading()

        observeMovieList()

        filterMovies(view)

        return view
    }

    private fun observeLoading() {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> loader.visibility = View.VISIBLE
                else -> loader.visibility = View.GONE
            }
        }
    }

    private fun observeMovieList() {
        viewModel.movieList.observe(this as LifecycleOwner) { movieList ->
            if(movieList.isNotEmpty()) {
                search.visibility = View.VISIBLE
                setupList(movieList)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
    }

    private fun setupList(
        movieList: List<Movie>
    ) {
        movie_list.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        adapter = MyItemRecyclerViewAdapter(movieList)

        movie_list.adapter = adapter
    }

    private fun filterMovies(view: View) {
        view.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieFragment().apply {
            }
    }
}