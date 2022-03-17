package com.farid.proyekfootballeague.ui.detail.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity

class TeamViewModel (
    private val ligaRepository: LigaRepository
): ViewModel(){

    lateinit var id: String

    lateinit var query: String

    val team: LiveData<List<TeamsEntity>> get() =  ligaRepository.getAllTeamLeague(id)

    val searchTeam: LiveData<List<TeamsEntity>> get() = ligaRepository.getSearchTeam(query)
}