package com.mubassyir.submission2jetpack.data.remote.response


import com.google.gson.annotations.SerializedName

data class TvShowDetailsResponse(


    @SerializedName("first_air_date")
    val firstAirDate: String,

    val id: Int,

    val name: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

)