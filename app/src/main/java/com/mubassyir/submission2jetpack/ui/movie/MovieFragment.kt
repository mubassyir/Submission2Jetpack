package com.mubassyir.submission2jetpack.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.utils.Coroutines
import com.mubassyir.submission2jetpack.utils.hide
import com.mubassyir.submission2jetpack.utils.show
import com.mubassyir.submission2jetpack.utils.snackbar
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MovieFragment : Fragment(), KodeinAware,Listener {

    override val kodein by kodein()
    private val factory: MovieViewModelFactory by instance()
    private val movieAdapter = MovieAdapter()
    private lateinit var viewModel : MovieViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        fetchMovies()
        swipeRefreshMovie.setOnRefreshListener { fetchMovies() }

    }

    private fun fetchMovies(){
        viewModel.listener = this
        with(rv_movie) {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        viewModel.movie().observe(this, Observer {
            movieAdapter.setMovie(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onErrorMessage(message: String) {
        base_fragment.snackbar(message)
        movie_progressbar.hide()
        swipeRefreshMovie.isRefreshing = false
    }
    override fun onStarted() {
       movie_progressbar.show()
    }
    override fun onFinished() {
        movie_progressbar.hide()
        swipeRefreshMovie.isRefreshing = false
    }

}
