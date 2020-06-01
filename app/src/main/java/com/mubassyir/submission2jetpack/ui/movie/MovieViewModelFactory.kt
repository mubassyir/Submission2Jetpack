package com.mubassyir.submission2jetpack.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mubassyir.submission2jetpack.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}