package com.mubassyir.submission2jetpack.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mubassyir.submission2jetpack.BuildConfig
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.data.remote.response.TvShowResult
import com.mubassyir.submission2jetpack.details.DetailsActivity
import com.mubassyir.submission2jetpack.details.DetailsActivity.Companion.CLICK_ID
import com.mubassyir.submission2jetpack.details.DetailsActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.grid_tv_show.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    companion object {
        private const val IMAGE_URL = BuildConfig.IMAGE_URL
    }

    fun setMovie(it: List<TvShowResult>) {
        this.tvShow.clear()
        this.tvShow.addAll(it)
    }

    private var tvShow = ArrayList<TvShowResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tvShow.size
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShow[position]
        holder.bind(tvShow)
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowResult) {
            with(itemView) {
                Glide.with(itemView)
                    .load(IMAGE_URL + tvShow.posterPath)
                    .error(R.drawable.image_not_found)
                    .into(tv_show_cover)
                tv_show_title.text = tvShow.originalName

                itemView.setOnClickListener {
                    Intent(context, DetailsActivity::class.java).also {
                        it.putExtra(CLICK_ID, 0)
                        it.putExtra(EXTRA_ID, tvShow.id)
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }
}