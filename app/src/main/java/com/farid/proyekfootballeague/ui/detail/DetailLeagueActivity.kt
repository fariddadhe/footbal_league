package com.farid.proyekfootballeague.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.Leagues
import com.farid.proyekfootballeague.ui.detail.nextmatch.NextMatchFragment
import com.farid.proyekfootballeague.ui.detail.previousmatch.PreviousMatchFragment
import com.farid.proyekfootballeague.ui.detail.standings.StandingsActivity
import com.farid.proyekfootballeague.ui.detail.team.TeamActivity
import com.farid.proyekfootballeague.utils.replaceFragment
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    private var viewModel: DetailLeagueViewModel? = null
    private var league: Leagues? = null
    private val MENU_SELECTED: String = "menu_selected"
    private var id: Int = R.id.previousMatch
    private var idLeague = ""
    private var nameLeague = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        viewModel = obtainViewModel(this)

        populateLeague()

        if (savedInstanceState != null) {
            id = savedInstanceState.getInt(MENU_SELECTED)
            setVisibility(id)
        } else {
            loadFragment("previousmatch")
        }

        previousMatch.setOnClickListener {
            id = R.id.previousMatch
            loadFragment("previousmatch")
            setVisibility(id)
        }

        nextMatch.setOnClickListener {
            id = R.id.nextMatch
            loadFragment("nextmatch")
            setVisibility(id)
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgMenu.setOnClickListener {
            showMenu(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MENU_SELECTED, id)
    }

    private fun populateLeague() {
        league = intent.getParcelableExtra(extra_id)
        idLeague = league?.id.toString()

        viewModel?.id = league?.id.toString()

        viewModel?.detail?.observe(this, Observer { list ->
            list.get(0).strPoster.let {
                Glide.with(this)
                    .load(it)
                    .apply(RequestOptions.centerCropTransform())
                    .into(imgPoster)
            }
            tvLeagueName.text = list.get(0).strLeague
            tvLeagueDescription.text = list.get(0).strDescriptionEN

        })
    }

    private fun setVisibility(id: Int) {
        if (id == R.id.nextMatch) {
            undPreviousMatch.visibility = View.INVISIBLE
            undNextMatch.visibility = View.VISIBLE
        } else {
            undPreviousMatch.visibility = View.VISIBLE
            undNextMatch.visibility = View.INVISIBLE
        }
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): DetailLeagueViewModel {
        val factory = ViewModelFactory.getInstance(application)

        return ViewModelProviders.of(appCompatActivity, factory)
            .get(DetailLeagueViewModel::class.java)
    }

    private fun loadFragment(fragment: String) {
        if (fragment == "nextmatch") {
            NextMatchFragment.idLeague = league?.id.toString()
            NextMatchFragment.newInstance().let {
                replaceFragment(it, R.id.containerDetail)
            }
        } else {
            PreviousMatchFragment.idLeague = league?.id.toString()
            PreviousMatchFragment.newInstance().let {
                replaceFragment(it, R.id.containerDetail)
            }
        }
    }

    private fun showMenu(v: View) {
        val popup = PopupMenu(this, v)
        popup.inflate(R.menu.league_menu)
        popup.show()

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.table_menu -> {
                    val intent = Intent(this, StandingsActivity::class.java)
                    intent.putExtra(StandingsActivity.id, league)
                    startActivity(intent)
                    true
                }
                R.id.team_menu -> {
                    val intent = Intent(this, TeamActivity::class.java)
                    intent.putExtra(TeamActivity.id, league)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    companion object {
        const val extra_id = ""
    }
}

