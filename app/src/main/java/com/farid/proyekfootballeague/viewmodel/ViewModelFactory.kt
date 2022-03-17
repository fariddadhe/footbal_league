package com.farid.proyekfootballeague.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.di.Injection
import com.farid.proyekfootballeague.ui.detail.DetailLeagueViewModel
import com.farid.proyekfootballeague.ui.detail.detailteam.DetailTeamViewModel
import com.farid.proyekfootballeague.ui.detail.nextmatch.NextMatchViewModel
import com.farid.proyekfootballeague.ui.detail.previousmatch.PreviousMatchViewModel
import com.farid.proyekfootballeague.ui.detail.standings.StandingsViewModel
import com.farid.proyekfootballeague.ui.detail.team.TeamViewModel
import com.farid.proyekfootballeague.ui.detailmatch.DetailMatchViewModel
import com.farid.proyekfootballeague.ui.favorite.nextmatch.FavoriteNextMatchViewModel
import com.farid.proyekfootballeague.ui.favorite.previousmatch.FavoritePreviousMatchViewModel
import com.farid.proyekfootballeague.ui.favorite.team.FavoriteTeamViewModel
import com.farid.proyekfootballeague.ui.league.LeagueViewModel
import com.farid.proyekfootballeague.ui.searchmatch.SearchMatchViewModel

class ViewModelFactory private constructor(
    private val application: Application,
    private val ligaRepository: LigaRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(DetailLeagueViewModel::class.java) ->
                DetailLeagueViewModel(ligaRepository)
            isAssignableFrom(NextMatchViewModel::class.java) ->
                NextMatchViewModel(ligaRepository)
            isAssignableFrom(PreviousMatchViewModel::class.java) ->
                PreviousMatchViewModel(ligaRepository)
            isAssignableFrom(DetailMatchViewModel::class.java) ->
                DetailMatchViewModel(ligaRepository)
            isAssignableFrom(LeagueViewModel::class.java) ->
                LeagueViewModel(ligaRepository)
            isAssignableFrom(SearchMatchViewModel::class.java) ->
                SearchMatchViewModel(ligaRepository)
            isAssignableFrom(FavoriteNextMatchViewModel::class.java) ->
                FavoriteNextMatchViewModel(ligaRepository)
            isAssignableFrom(FavoritePreviousMatchViewModel::class.java) ->
                FavoritePreviousMatchViewModel(ligaRepository)
            isAssignableFrom(StandingsViewModel::class.java)->
                StandingsViewModel(ligaRepository)
            isAssignableFrom(TeamViewModel::class.java)->
                TeamViewModel(ligaRepository)
            isAssignableFrom(DetailTeamViewModel::class.java)->
                DetailTeamViewModel(ligaRepository)
            isAssignableFrom(FavoriteTeamViewModel::class.java)->
                FavoriteTeamViewModel(ligaRepository)
            else ->
                throw IllegalArgumentException("unkown viemodel class: ${modelClass.name}")
        }
    } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    application, Injection.provideRepository(application.applicationContext)
                ).also {
                    INSTANCE = it
                }
            }
    }
}