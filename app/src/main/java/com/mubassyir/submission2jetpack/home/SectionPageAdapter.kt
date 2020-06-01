package com.mubassyir.submission2jetpack.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mubassyir.submission2jetpack.R
import com.mubassyir.submission2jetpack.ui.movie.MovieFragment
import com.mubassyir.submission2jetpack.ui.tv_show.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val TAB_TITLES = intArrayOf(
        R.string.tab_movie,
        R.string.tab_tv_show
    )
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                MovieFragment()
            1 -> fragment =
                TvShowFragment()
        }
        return fragment as Fragment
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 2
    }
}