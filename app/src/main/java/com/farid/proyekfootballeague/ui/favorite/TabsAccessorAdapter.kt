package com.farid.proyekfootballeague.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.farid.proyekfootballeague.ui.favorite.nextmatch.FavoriteNextMatchFragment
import com.farid.proyekfootballeague.ui.favorite.previousmatch.FavoritePreviousMatchFragment
import com.farid.proyekfootballeague.ui.favorite.team.FavoriteTeamFragment

class TabsAccessorAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        FavoritePreviousMatchFragment(),
        FavoriteNextMatchFragment(),
        FavoriteTeamFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Previous Match"
            1 -> "Next Match"
            else -> "Team"
        }
    }

}