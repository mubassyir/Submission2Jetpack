package com.mubassyir.submission2jetpack.data.remote


import android.content.Context
import com.mubassyir.submission2jetpack.data.remote.response.MovieDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.MovieResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    //"http://api.themoviedb.org/3/discover/tv?api_key=$API_KEY"
    //"http://api.themoviedb.org/3/discover/movie?api_key=09be981841d6d49c416bfe1d5824f59a"

    @GET("discover/movie")
    suspend fun getAllMovie(@Query("api_key") apiKey: String?): Response<MovieResponse>

    @GET("discover/tv")
    suspend fun getAllTvShow(@Query("api_key") apiKey: String?) :Response<TvShowResponse>


    @GET ("movie/{movie_id}")
    suspend fun getMovieDetils(
        @Path("movie_id")id:Int,
        @Query("api_key")key:String
    ): Response<MovieDetailsResponse>

    @GET ("tv/{tv_id}")
    suspend fun getTvShowDetils(
        @Path("tv_id")id:Int,
        @Query("api_key")key:String
    ): Response<TvShowDetailsResponse>

    companion object{
        operator fun invoke(interceptor: NetworkConnectionInterceptor): ApiHelper{
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiHelper::class.java)
        }
    }
}