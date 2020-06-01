package com.mubassyir.submission2jetpack.ui.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mubassyir.submission2jetpack.BuildConfig
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.data.remote.response.MovieResult
import com.mubassyir.submission2jetpack.details.DetailsActivity
import com.mubassyir.submission2jetpack.details.DetailsActivity.Companion.CLICK_ID
import com.mubassyir.submission2jetpack.details.DetailsActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.grid_movie.view.*

class MovieAdapter:RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object{
        private const val IMAGE_URL = BuildConfig.IMAGE_URL
    }

    fun setMovie(i:List<MovieResult>){

            listMovie.clear()
            listMovie.addAll(i)
    }

    private var listMovie = ArrayList<MovieResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_movie,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    inner class MovieViewHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bind(movie:MovieResult){
            with(itemView){
                movie_title.text = movie.original_title
                Glide.with(context)
                    .load(IMAGE_URL+movie.poster_path)
                    .error(R.drawable.image_not_found)
                    .into(movie_cover)

                itemView.setOnClickListener {
                    Intent(context,DetailsActivity::class.java).also {
                        it.putExtra(CLICK_ID,1)
                        it.putExtra(EXTRA_ID,movie.id)
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }
}