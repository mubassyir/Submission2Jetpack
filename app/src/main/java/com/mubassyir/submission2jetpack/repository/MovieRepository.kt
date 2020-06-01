package com.mubassyir.submission2jetpack.repository

import com.mubassyir.submission2jetpack.BuildConfig
import com.mubassyir.submission2jetpack.data.remote.ApiHelper
import com.mubassyir.submission2jetpack.data.remote.SafeApiRequest
import com.mubassyir.submission2jetpack.data.remote.response.MovieDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.MovieResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowResponse
import com.mubassyir.submission2jetpack.utils.NoInternetException


//09be981841d6d49c416bfe1d5824f59a

class MovieRepository(private val api:ApiHelper): SafeApiRequest(){
    companion object{
        const val API_KEY = BuildConfig.API_KEY
    }

    suspend fun getAllMovie():MovieResponse?{
        return apiRequest { api.getAllMovie(API_KEY) }
    }

    suspend fun getAllTvShow():TvShowResponse{
        return apiRequest { api.getAllTvShow(API_KEY) }
    }

    suspend fun getDetailMovie(id:Int):MovieDetailsResponse{
        return apiRequest { api.getMovieDetils(id, API_KEY )}
    }

    suspend fun getDetailsTvShow(id:Int):TvShowDetailsResponse{
        return apiRequest { api.getTvShowDetils(id, API_KEY) }
    }

}