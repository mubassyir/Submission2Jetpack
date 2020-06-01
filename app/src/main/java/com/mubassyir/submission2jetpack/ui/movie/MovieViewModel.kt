package com.mubassyir.submission2jetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mubassyir.submission2jetpack.data.remote.response.MovieResult
import com.mubassyir.submission2jetpack.repository.MovieRepository
import com.mubassyir.submission2jetpack.utils.Coroutines
import com.mubassyir.submission2jetpack.utils.ApiExeptions
import com.mubassyir.submission2jetpack.utils.NoInternetException
import com.mubassyir.submission2jetpack.vo.Resource

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movieLive = MutableLiveData<List<MovieResult>>()
    var listener:Listener?=null

    fun movie(): LiveData<List<MovieResult>> {
        Coroutines.main {
            try {
                movieLive.postValue(repository.getAllMovie()?.results).let {
                    listener?.onFinished()
                }
            }catch(e:ApiExeptions){
                listener?.onErrorMessage(e.message!!)
            } catch (e:NoInternetException){
                listener?.onErrorMessage(e.message!!)
            }
        }
        return movieLive
    }

}