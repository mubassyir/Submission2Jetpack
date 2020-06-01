package com.mubassyir.submission2jetpack.data.remote.response


import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(

    @SerializedName("original_title")
    var originalTitle: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)