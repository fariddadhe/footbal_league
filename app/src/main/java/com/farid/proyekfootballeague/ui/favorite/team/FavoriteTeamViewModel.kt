package com.farid.proyekfootballeague.ui.favorite.team

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.local.db.FavoriteTeam
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity

class FavoriteTeamViewModel (
    private val ligaRepository: LigaRepository
): ViewModel(){

    lateinit var table: String

    lateinit var context: Context

    val team: LiveData<List<TeamsEntity>> get() = ligaRepository.favoriteTeam(table, context)
}