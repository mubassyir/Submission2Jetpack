package com.mubassyir.submission2jetpack.ui.movie

interface Listener{
    fun onStarted()
    fun onFinished()
    fun onErrorMessage(message:String)
}