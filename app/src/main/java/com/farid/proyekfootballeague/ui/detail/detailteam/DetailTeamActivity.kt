package com.farid.proyekfootballeague.ui.detail.detailteam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.local.db.FavoriteTeam
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import com.farid.proyekfootballeague.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.item_detail_team.*
import kotlinx.android.synthetic.main.item_team.imgTeamLogo
import kotlinx.android.synthetic.main.item_team.tvTeamName
import org.jetbrains.anko.contentView

class DetailTeamActivity : AppCompatActivity() {

    private var viewModel: DetailTeamViewModel? = null
    private var teamsEntity: TeamsEntity? = null
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        viewModel = obtainViewModel(this)

        imgBack.setOnClickListener {
            onBackPressed()
        }

        imgFavorite.setOnClickListener {
            if (isFavorite) removeFromFavrite() else addToFavorite()
            isFavorite = !isFavorite
            setFavorite()
        }

        populateteam()
        setFavorite()
    }

    private fun populateteam() {
        teamsEntity = intent.getParcelableExtra(extra_team)

        viewModel?.context = applicationContext
        viewModel?.table = FavoriteTeam.TABLE_FAVORITE_TEAM
        viewModel?.id = teamsEntity?.idTeam.toString()

        Glide.with(this)
            .load(teamsEntity?.strTeamBadge)
            .into(imgTeamLogo)
        tvTeamName.text = teamsEntity?.strTeam
        tvStadium.text = teamsEntity?.strStadium
        tvDescription.text = teamsEntity?.strDescriptionEN

        favoriteState()
    }

    private fun addToFavorite() {
        viewModel?.team = teamsEntity!!
        viewModel?.addFavorite?.let {
            Snackbar.make(contentView!!, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun removeFromFavrite() {
        viewModel?.removeFavorite?.let {
            Snackbar.make(contentView!!, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun favoriteState() {
        viewModel?.favorteState.let {
            if (it == true) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            imgFavorite.setImageResource(R.drawable.ic_star)
        } else {
            imgFavorite.setImageResource(R.drawable.ic_star_border)
        }
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): DetailTeamViewModel {
        val factory = ViewModelFactory.getInstance(application)

        return ViewModelProviders.of(appCompatActivity, factory)
            .get(DetailTeamViewModel::class.java)
    }

    companion object {
        const val extra_team = "team"
    }
}
