package com.farid.proyekfootballeague.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.ui.league.LeagueFragment
import com.farid.proyekfootballeague.ui.searchmatch.SearchMatchFragment
import com.farid.proyekfootballeague.utils.replaceFragmentWithTag
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val MENU_SELECTED: String = "menu_selected"

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            loadFragment(item.itemId)
            newInstance(this, item.itemId)
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val navigationId = intent.getIntExtra(PARAM_NAVIGATION_ID, R.id.league_nav)
        if (savedInstanceState != null) {
            savedInstanceState.getInt(MENU_SELECTED)
        } else {
            nav_view.selectedItemId = navigationId
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MENU_SELECTED, nav_view.selectedItemId)
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.league_nav -> {
                LeagueFragment.newInstance()
            }
            R.id.search_nav -> {
                SearchMatchFragment.newInstance()
            }
            else -> {
                null
            }
        }

        if (fragment != null) {
            replaceFragmentWithTag(fragment, tag, R.id.container_main)
        }
    }


    companion object {
        const val PARAM_NAVIGATION_ID = "navigation_id"

        fun newInstance(context: Context, navigationId: Int) =
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(PARAM_NAVIGATION_ID, navigationId)
            }
    }
}
