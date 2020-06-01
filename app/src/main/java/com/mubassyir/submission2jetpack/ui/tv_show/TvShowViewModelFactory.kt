package com.mubassyir.submission2jetpack.ui.tv_show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mubassyir.submission2jetpack.repository.MovieRepository
import com.mubassyir.submission2jetpack.ui.movie.MovieViewModel

@Suppress("UNCHECKED_CAST")
class TvShowViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(repository) as T
    }
}