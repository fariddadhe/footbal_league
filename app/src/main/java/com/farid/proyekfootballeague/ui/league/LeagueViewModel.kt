package com.farid.proyekfootballeague.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.Leagues

class LeagueViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {

    val league: LiveData<List<Leagues>> get() = ligaRepository.getLeagues()
}