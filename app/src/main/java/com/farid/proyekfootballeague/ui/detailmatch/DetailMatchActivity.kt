package com.farid.proyekfootballeague.ui.detailmatch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.utils.getDate
import com.farid.proyekfootballeague.utils.getTime
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.contentView

class DetailMatchActivity : AppCompatActivity() {

    private var viewModel: DetailMatchViewModel? = null
    private var list: MutableList<EventEntity> = mutableListOf()
    private var isfavorite: Boolean = false
    private var dateEvent: String? = ""
    private var time: String? = ""
    lateinit var favorite: FavoriteMatch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)


        viewModel = obtainViewModel(this)

        progress_barr.visibility = View.VISIBLE

        getData()

        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgFavorite.setOnClickListener {
            if (isfavorite) removeFromFavorite() else addToFavorite()
            isfavorite = !isfavorite
            setFavorite()

        }
    }

    private fun getData() {

        viewModel?.context = applicationContext
        viewModel?.table = FavoriteMatch.TABLE_FAVORITE_MATCH

        val intent = intent
        val idEvent = intent.getStringExtra(extra_event)


        if (idEvent != null) {
            viewModel?.id = idEvent.toString()

            viewModel?.detail?.observe(this, Observer { listres ->
                list = listres as MutableList<EventEntity>
                populateDetail()
                favoriteState()
                setFavorite()
            })
        } else {
            favorite = intent.getParcelableExtra(extra_parcel)
            viewModel?.id = favorite.eventId.toString()
            populateParcelable()
            favoriteState()
            setFavorite()
        }
    }


    private fun populateDetail() {
        getHomeTeam(list.get(0).idHomeTeam.toString())
        getAwayTeam(list.get(0).idAwayTeam.toString())


        if (list.get(0).strAwayLineupGoalkeeper != "") {
            tvHomeScore.text = list.get(0).intHomeScore.toString()
            tvAwayScore.text = list.get(0).intAwayScore.toString()
        } else {
            tvHomeScore.text = "?"
            tvAwayScore.text = "?"
        }

        tvHomeTeam.text = list.get(0).strHomeTeam
        tvAwayTeam.text = list.get(0).strAwayTeam
        tvHomeGoals.text = list.get(0).strHomeGoalDetails
        tvAwayGoals.text = list.get(0).strAwayGoalDetails
        tvHomeGoalkeeper.text = list.get(0).strHomeLineupGoalkeeper
        tvAwayGoalkeeper.text = list.get(0).strAwayLineupGoalkeeper
        tvHomeDefense.text = list.get(0).strHomeLineupDefense
        tvAwayDefense.text = list.get(0).strAwayLineupDefense
        tvHomeMidfield.text = list.get(0).strHomeLineupMidfield
        tvAwayMidfield.text = list.get(0).strAwayLineupMidfield
        tvHomeForward.text = list.get(0).strHomeLineupForward
        tvAwayForward.text = list.get(0).strAwayLineupForward
        tvHomeSubstitutes.text = list.get(0).strHomeLineupSubstitutes
        tvAwaySubstitutes.text = list.get(0).strAwayLineupSubstitutes
        tvHomeShots.text = list.get(0).intHomeShots
        tvAwayShots.text = list.get(0).intAwayShots
        dateEvent = getDate(list.get(0).dateEvent)
        time = getTime(list.get(0).strTime)
        tvDateMatch.text = "${dateEvent} | ${time}"
    }

    private fun populateParcelable() {
        getHomeTeam(favorite.homeTeamId.toString())
        getAwayTeam(favorite.awayTeamId.toString())


        if (favorite.awayLineUpGoalKeeper != "") {
            tvHomeScore.text = favorite.homeScore
            tvAwayScore.text = favorite.awayScore
        } else {
            tvHomeScore.text = "?"
            tvAwayScore.text = "?"
        }

        tvHomeTeam.text = favorite.homeTeam
        tvAwayTeam.text = favorite.awayTeam
        tvHomeGoals.text = favorite.homeGoalDetail
        tvAwayGoals.text = favorite.awayGoalDetail
        tvHomeGoalkeeper.text = favorite.homeLineUpGoalKeeper
        tvAwayGoalkeeper.text = favorite.awayLineUpGoalKeeper
        tvHomeDefense.text = favorite.homeLineUpDefense
        tvAwayDefense.text = favorite.awayLineUpDefense
        tvHomeMidfield.text = favorite.homeLineUpMidField
        tvAwayMidfield.text = favorite.awayLineUpMidField
        tvHomeForward.text = favorite.homeLineUpForward
        tvAwayForward.text = favorite.awayLineUpForward
        tvHomeSubstitutes.text = favorite.homeLineUpSubstitutes
        tvAwaySubstitutes.text = favorite.awayLineUpSubstitutes
        tvHomeShots.text = favorite.homeShots
        tvAwayShots.text = favorite.awayShots
        tvDateMatch.text = "${favorite.dateEvent} | ${favorite.time}"
    }

    private fun addToFavorite() {
        viewModel?.list = list
        viewModel?.addFavorite?.let {
            Snackbar.make(contentView!!, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun removeFromFavorite() {
        viewModel?.removeFavorite?.let {
            Snackbar.make(contentView!!, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun favoriteState() {
        viewModel?.favoriteState.let {
            if (it == true) isfavorite = true
        }
    }

    private fun setFavorite() {
        if (isfavorite)
            imgFavorite.setImageResource(R.drawable.ic_star)
        else
            imgFavorite.setImageResource(R.drawable.ic_star_border)
    }

    private fun getHomeTeam(id: String) {
        viewModel?.idTeams = id
        viewModel?.teams?.observe(this, Observer { teams ->
            Glide.with(this)
                .load(teams.get(0).strTeamBadge)
                .into(imgHomeLogo)

        })
    }

    private fun getAwayTeam(id: String) {
        viewModel?.idTeams = id
        viewModel?.teams?.observe(this, Observer { teams ->
            Glide.with(this)
                .load(teams.get(0).strTeamBadge)
                .into(imgAwayLogo)
            progress_barr.visibility = View.GONE
        })
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): DetailMatchViewModel {
        val factory = ViewModelFactory.getInstance(application)

        return ViewModelProviders.of(appCompatActivity, factory)
            .get(DetailMatchViewModel::class.java)
    }

    companion object {
        const val extra_event = "id"
        const val extra_parcel = "parcel"
    }
}