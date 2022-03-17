package com.farid.proyekfootballeague.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.LeaguesDetail

class DetailLeagueViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {

    lateinit var id: String

    val detail: LiveData<List<LeaguesDetail>> get() = ligaRepository.getDetailLeague(id)
}