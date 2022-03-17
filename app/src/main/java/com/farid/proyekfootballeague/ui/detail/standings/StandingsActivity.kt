package com.farid.proyekfootballeague.ui.detail.standings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.Leagues
import com.farid.proyekfootballeague.data.source.remote.response.TableEntity
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_standings.*

class StandingsActivity : AppCompatActivity() {

    private var viewModel: StandingsViewModel? = null
    private var tableList: List<TableEntity> = listOf()
    private var league: Leagues? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        viewModel = obtainViewModel(this)

        league = intent.getParcelableExtra(id)

        setActionBar()

        progress_bar.visibility = View.VISIBLE
        viewModel?.id = league?.id.toString()
        viewModel?.standings?.observe(this, Observer { list ->
            tableList = list
            setAdapter()
        })
    }

    private fun setAdapter(){
        progress_bar.visibility = View.GONE
        rv_standings.adapter = StandingsAdapter(this, tableList)
        rv_standings.layoutManager = LinearLayoutManager(this)
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): StandingsViewModel {
        val factory = ViewModelFactory.getInstance(application)

        return ViewModelProviders.of(appCompatActivity, factory)
            .get(StandingsViewModel::class.java)
    }

    private fun setActionBar(){
        supportActionBar?.title = league?.name.toString()
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    companion object {
        const val id = ""
    }
}
