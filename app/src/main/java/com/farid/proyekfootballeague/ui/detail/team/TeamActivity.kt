package com.farid.proyekfootballeague.ui.detail.team

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.Leagues
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {

    private var viewmodel: TeamViewModel? = null
    private var teamList: List<TeamsEntity> = listOf()
    private var league: Leagues? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        viewmodel = obtainViewModel(this)

        league = intent.getParcelableExtra(id)

        setActionBar()

        showLoading(true)

        viewmodel?.id = league?.id.toString()
        viewmodel?.team?.observe(this, Observer { list ->
            teamList = list
            setAdapter()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.clear()
        menuInflater.inflate(R.menu.main_menu, menu)

        val itemFavorite = menu?.findItem(R.id.favorite)
        itemFavorite?.isVisible = false

        val itemSearch = menu?.findItem(R.id.search)

        val searcView: SearchView = itemSearch?.actionView as SearchView

        searcView.queryHint = "Search Team"

        searcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searcView.clearFocus()
                showLoading(true)
                searchTeam(query.toString())
                return true
            }

            override fun onQueryTextChange(newTex: String?): Boolean {
                if (newTex.toString().isNotEmpty()) {
                    showLoading(true)
                    searchTeam(newTex.toString())
                }
                return true
            }

        })

        return true
    }

    private fun searchTeam(query: String) {
        viewmodel?.query = query

        viewmodel?.searchTeam?.observe(this, Observer { list ->
            if (list.isNullOrEmpty()) {
                showLoading(false)
                Toast.makeText(this, "data tidak ditemukan", Toast.LENGTH_SHORT).show()
            } else {
                teamList = list
                setAdapter()
            }
        })
    }

    private fun setAdapter() {
        showLoading(false)
        rv_team.adapter = TeamAdapter(this, teamList)
        rv_team.layoutManager = GridLayoutManager(this, 2)
    }

    companion object {
        const val id = ""
    }

    private fun setActionBar() {
        supportActionBar?.title = league?.name.toString()
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): TeamViewModel {
        val factory = ViewModelFactory.getInstance(application)

        return ViewModelProviders.of(appCompatActivity, factory)
            .get(TeamViewModel::class.java)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}
