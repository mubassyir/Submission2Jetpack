package com.mubassyir.submission2jetpack.details

import android.graphics.Color
import android.net.sip.SipSession
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.mubassyir.submission2jetpack.BuildConfig
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.data.remote.response.MovieDetailsResponse
import com.mubassyir.submission2jetpack.data.remote.response.TvShowDetailsResponse
import com.mubassyir.submission2jetpack.ui.movie.Listener
import com.mubassyir.submission2jetpack.utils.hide
import com.mubassyir.submission2jetpack.utils.show
import com.mubassyir.submission2jetpack.utils.snackbar
import kotlinx.android.synthetic.main.activity_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class DetailsActivity : AppCompatActivity(),KodeinAware,Listener {

    override val kodein by kodein()
    private val factory : DetailsViewModelFactory by instance()

    private lateinit var appBar: AppBarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout

    companion object{
        const val IMAGE_URL = BuildConfig.IMAGE_URL_LARGE
        const val CLICK_ID = "click"
        const val EXTRA_ID = "extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movieViewModel = ViewModelProvider(this,factory).get(DetailsViewModel::class.java)


        if (intent.getIntExtra(CLICK_ID,1)==1){
            movieViewModel.listener=this
            movieViewModel.getDetailsMovie(intent.getIntExtra(EXTRA_ID,0)).observe(this, Observer {
                    movieDetails -> movieBinding(movieDetails)
            })
        } else{
            movieViewModel.listener=this
            movieViewModel.getDetailsTvShoe(intent.getIntExtra(EXTRA_ID,0)).observe(this, Observer {
                tvShowDetails -> tvShowBinding(tvShowDetails)
            })
        }

        toolbar = findViewById(R.id.toolbar)
        appBar = findViewById(R.id.app_bar)
        collapsingToolbar = findViewById(R.id.toolbar_layout)

        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
                toolbar.setBackgroundColor(Color.BLACK)
            } else {
                toolbar.setBackgroundColor(Color.TRANSPARENT)
                collapsingToolbar.setExpandedTitleColor(Color.WHITE)
            }
        })
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun tvShowBinding(tvShowDetail: TvShowDetailsResponse) {
        collapsingToolbar.setTitle(tvShowDetail.originalName)

        Glide.with(this)
            .load(IMAGE_URL+tvShowDetail.posterPath)
            .error(R.drawable.image_not_found)
            .into(movie_cover)
        movie_title.text = tvShowDetail.originalName
        release_date.text = tvShowDetail.firstAirDate
        movie_rating.text = tvShowDetail.voteAverage.toString()
        movie_description.text= tvShowDetail.overview
    }

    private fun movieBinding(movieDetail: MovieDetailsResponse) {
        collapsingToolbar.title = movieDetail.originalTitle

        Glide.with(this)
            .load(IMAGE_URL+movieDetail.posterPath)
            .error(R.drawable.loading)
            .into(movie_cover)

        movie_title.text = movieDetail.originalTitle
        release_date.text = movieDetail.releaseDate
        movie_rating.text = movieDetail.voteAverage.toString()
        movie_description.text= movieDetail.overview
    }

    override fun onStarted() {
        details_progressbar.show()
    }

    override fun onFinished() {
        details_progressbar.hide()
    }

    override fun onErrorMessage(message: String) {
        baseDetails.snackbar(message)
        details_progressbar.hide()
    }

}
