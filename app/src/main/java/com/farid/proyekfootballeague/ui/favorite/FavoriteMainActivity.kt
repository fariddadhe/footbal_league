package com.farid.proyekfootballeague.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farid.proyekfootballeague.R
import kotlinx.android.synthetic.main.activity_favorite_main.*

class FavoriteMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_main)

        initAppBar()
    }

    private fun initAppBar() {
        setSupportActionBar(main_page_toolbar)
        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        main_tabs_pager.adapter =
            TabsAccessorAdapter(
                supportFragmentManager
            )
        main_tabs.setupWithViewPager(main_tabs_pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
