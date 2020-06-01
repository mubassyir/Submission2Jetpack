package com.mubassyir.submission2jetpack.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<MovieResult>? )