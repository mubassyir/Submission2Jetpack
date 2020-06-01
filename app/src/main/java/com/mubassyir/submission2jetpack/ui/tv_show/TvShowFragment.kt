package com.mubassyir.submission2jetpack.ui.tv_show


import android.net.sip.SipSession
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.ui.movie.Listener
import com.mubassyir.submission2jetpack.utils.Coroutines
import com.mubassyir.submission2jetpack.utils.hide
import com.mubassyir.submission2jetpack.utils.show
import com.mubassyir.submission2jetpack.utils.snackbar
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment(),KodeinAware,Listener {

    override val kodein by kodein()
    private val factory :TvShowViewModelFactory by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvViewModel = ViewModelProvider(this,factory).get(TvShowViewModel::class.java)
        tvViewModel.listener=this

        val tvAdapter = TvShowAdapter()
            tvViewModel.getAllTvShow().observe(this, Observer{
            tvAdapter.setMovie(it)
            tvAdapter.notifyDataSetChanged()
        })

        with(rv_tv_show){
            adapter = tvAdapter
            layoutManager = GridLayoutManager(context,3)
            setHasFixedSize(true)
        }
    }

    override fun onStarted() {
        tv_show_progress_bar.show()
    }

    override fun onFinished() {
        tv_show_progress_bar.hide()
    }

    override fun onErrorMessage(message: String) {
       tvShowBase.snackbar(message)
    }


}
