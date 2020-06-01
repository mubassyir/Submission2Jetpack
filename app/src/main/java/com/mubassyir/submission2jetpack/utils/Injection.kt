package com.mubassyir.submission2jetpack.utils

import android.app.Application
import com.mubassyir.submission2jetpack.data.remote.ApiHelper
import com.mubassyir.submission2jetpack.data.remote.NetworkConnectionInterceptor
import com.mubassyir.submission2jetpack.details.DetailsViewModel
import com.mubassyir.submission2jetpack.details.DetailsViewModelFactory
import com.mubassyir.submission2jetpack.repository.MovieRepository
import com.mubassyir.submission2jetpack.ui.movie.MovieViewModel
import com.mubassyir.submission2jetpack.ui.movie.MovieViewModelFactory
import com.mubassyir.submission2jetpack.ui.tv_show.TvShowViewModel
import com.mubassyir.submission2jetpack.ui.tv_show.TvShowViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Injection : Application(),KodeinAware{
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@Injection))

       //ConnectionInterceptor Injection
        bind() from singleton { NetworkConnectionInterceptor(instance())}

        //Repository Injection
        bind() from singleton { MovieRepository(instance()) }

        //ApiHelper Injection
        bind() from singleton { ApiHelper(instance()) }

        //MovieViewModel Ijection
        bind() from provider { MovieViewModel(instance()) }
        bind() from provider { MovieViewModelFactory(instance())}

        //TvShowInjection
        bind() from provider { TvShowViewModel(instance()) }
        bind() from provider { TvShowViewModelFactory(instance()) }

        //DetailsViewModel Injection
        bind() from provider { DetailsViewModel(instance()) }
        bind() from provider { DetailsViewModelFactory(instance()) }
    }
}