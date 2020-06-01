package com.mubassyir.submission2jetpack.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mubassyir.submission2jetpack.repository.MovieRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository) as T
    }
}