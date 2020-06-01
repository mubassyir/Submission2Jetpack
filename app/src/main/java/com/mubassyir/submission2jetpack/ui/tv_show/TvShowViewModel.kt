package com.mubassyir.submission2jetpack.ui.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mubassyir.submission2jetpack.data.remote.response.TvShowResult
import com.mubassyir.submission2jetpack.repository.MovieRepository
import com.mubassyir.submission2jetpack.ui.movie.Listener
import com.mubassyir.submission2jetpack.utils.Coroutines
import com.mubassyir.submission2jetpack.utils.ApiExeptions
import com.mubassyir.submission2jetpack.utils.NoInternetException

class TvShowViewModel(private val repository: MovieRepository) :ViewModel(){

    var listener: Listener?=null
    val tvShowLive = MutableLiveData<List<TvShowResult>>()

    fun getAllTvShow():LiveData<List<TvShowResult>>{
        Coroutines.main {
            try {
                tvShowLive.postValue(repository.getAllTvShow().results)
                listener?.onFinished()
            }catch (e:ApiExeptions){
                listener?.onErrorMessage(e.message!!)
            } catch (e:NoInternetException){
                listener?.onErrorMessage(e.message!!)
            }
        }
        return tvShowLive
    }
}