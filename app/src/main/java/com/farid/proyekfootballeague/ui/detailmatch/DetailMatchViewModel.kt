package com.farid.proyekfootballeague.ui.detailmatch

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity

class DetailMatchViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {

    lateinit var id: String

    lateinit var idTeams: String

    lateinit var list: List<EventEntity>

    lateinit var table: String

    lateinit var context: Context

    val detail: LiveData<List<EventEntity>> get() = ligaRepository.getDetailMatch(id)

    val teams: LiveData<List<TeamsEntity>> get() = ligaRepository.getTeams(idTeams)

    val favoriteState: Boolean get() = ligaRepository.getFavoriteState(table, id, context)

    val addFavorite: String get() = ligaRepository.addFavorite(table, list, context)

    val removeFavorite: String get() = ligaRepository.removeFavorite(table, id, context)
}