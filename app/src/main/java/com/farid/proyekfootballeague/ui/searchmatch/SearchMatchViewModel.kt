package com.farid.proyekfootballeague.ui.searchmatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity

class SearchMatchViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {

    lateinit var query: String

    val search: LiveData<List<EventEntity>> get() = ligaRepository.getSearchMatch(query)

    val defaultSearch: LiveData<List<EventEntity>> get() = ligaRepository.getSearchMatch("arsenal")
}