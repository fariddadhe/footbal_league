package com.farid.proyekfootballeague.ui.detail.nextmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity

class NextMatchViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {
    lateinit var id: String

    val match: LiveData<List<EventEntity>> get() = ligaRepository.getNextMatch(id)
}