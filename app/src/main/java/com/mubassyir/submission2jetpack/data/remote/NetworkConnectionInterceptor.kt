package com.mubassyir.submission2jetpack.data.remote


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.mubassyir.submission2jetpack.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("No Internet Connections")
        return chain.proceed(chain.request())
    }

    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        var result= false

        if (Build.VERSION.SDK_INT > 23) {
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }

        } else {
            connectivityManager.let { cm ->
                cm?.activeNetworkInfo.apply {
                    result = when {
                        this?.type == ConnectivityManager.TYPE_MOBILE -> true
                        this?.type == ConnectivityManager.TYPE_WIFI -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }
}









