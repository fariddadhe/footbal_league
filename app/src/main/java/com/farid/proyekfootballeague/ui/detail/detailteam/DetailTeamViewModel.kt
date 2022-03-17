package com.farid.proyekfootballeague.ui.detail.detailteam

import android.content.Context
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity

class DetailTeamViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {

    lateinit var id: String

    lateinit var team: TeamsEntity

    lateinit var table: String

    lateinit var context: Context

    val favorteState: Boolean get() = ligaRepository.getFavoriteStateTeam(table, id, context)

    val addFavorite: String get() = ligaRepository.addFavoriteTeam(table, team, context)

    val removeFavorite: String get() = ligaRepository.removeFavoriteTeam(table, id, context)
}