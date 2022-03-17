package com.farid.proyekfootballeague.ui.favorite.previousmatch

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch

class FavoritePreviousMatchViewModel(
    private val ligaRepository: LigaRepository
) : ViewModel() {
    lateinit var table: String

    lateinit var event: String

    lateinit var context: Context

    val match: LiveData<List<FavoriteMatch>>
        get() = ligaRepository.favoriteMatch(
            table,
            event,
            context
        )

}