package com.farid.proyekfootballeague.ui.detail.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.TableEntity

class StandingsViewModel (
    private val ligaRepository: LigaRepository
): ViewModel(){

    lateinit var id: String

    val standings: LiveData<List<TableEntity>> get() = ligaRepository.getTableLeague(id)
}