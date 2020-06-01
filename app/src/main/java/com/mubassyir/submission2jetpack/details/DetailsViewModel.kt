package com.mubassyir.submission2jetpack.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mubassyir.submission2jetpack.data.remote.response.MovieDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowDetailsResponse
import com.mubassyir.submission2jetpack.repository.MovieRepository
import com.mubassyir.submission2jetpack.ui.movie.Listener
import com.mubassyir.submission2jetpack.utils.Coroutines
import com.mubassyir.submission2jetpack.utils.ApiExeptions
import com.mubassyir.submission2jetpack.utils.NoInternetException

class DetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private val detailsMovie = MutableLiveData<MovieDetailsResponse>()
    private val detailsTvShow = MutableLiveData<TvShowDetailsResponse>()
    var listener: Listener? = null

    fun getDetailsMovie(id: Int): LiveData<MovieDetailsResponse> {
        Coroutines.main {
            try {
                detailsMovie.postValue(repository.getDetailMovie(id))
                listener?.onFinished()
            } catch (e: NoInternetException) {
            listener?.onErrorMessage(e.message!!)
            } catch (e:ApiExeptions){
                listener?.onErrorMessage(e.message!!)
            }
        }
        return detailsMovie
    }

    fun getDetailsTvShoe(id: Int): LiveData<TvShowDetailsResponse> {
        Coroutines.main {
            try {
                detailsTvShow.postValue(repository.getDetailsTvShow(id))
                listener?.onFinished()
            } catch (e: Exception) {
                listener?.onErrorMessage(e.message!!)
            } catch (e: NoInternetException) {
                listener?.onErrorMessage(e.message!!)
            }
        }
        return detailsTvShow
    }
}
