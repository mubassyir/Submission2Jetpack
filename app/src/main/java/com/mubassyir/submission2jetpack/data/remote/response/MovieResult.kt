package com.mubassyir.submission2jetpack.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResult(

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val release_date: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val original_title: String?,

    @SerializedName("poster_path")
    val poster_path: String?,

    @SerializedName("vote_average")
    val vote_average: Double?,

)