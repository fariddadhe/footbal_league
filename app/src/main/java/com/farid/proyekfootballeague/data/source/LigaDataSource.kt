package com.farid.proyekfootballeague.data.source

import android.content.Context
import androidx.lifecycle.LiveData
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.data.source.remote.response.*

interface LigaDataSource {
    fun getDetailLeague(id: String): LiveData<List<LeaguesDetail>>

    fun getLeagues(): LiveData<List<Leagues>>

    fun getNextMatch(id: String): LiveData<List<EventEntity>>

    fun getPreviousMatch(id: String): LiveData<List<EventEntity>>

    fun getDetailMatch(id: String): LiveData<List<EventEntity>>

    fun getTeams(id: String): LiveData<List<TeamsEntity>>

    fun getSearchMatch(query: String): LiveData<List<EventEntity>>

    fun getSearchTeam(t: String): LiveData<List<TeamsEntity>>

    fun getAllTeamLeague(id: String): LiveData<List<TeamsEntity>>

    fun getTableLeague(id: String): LiveData<List<TableEntity>>

    fun getFavoriteState(table: String, id: String, context: Context): Boolean

    fun addFavorite(table: String, list: List<EventEntity>, context: Context): String

    fun removeFavorite(table: String, id: String, context: Context): String

    fun favoriteMatch(table: String, event: String, context: Context): LiveData<List<FavoriteMatch>>

    fun getFavoriteStateTeam(table: String, id: String, context: Context): Boolean

    fun addFavoriteTeam(table: String, team: TeamsEntity, context: Context): String

    fun removeFavoriteTeam(table: String, id: String, context: Context): String

    fun favoriteTeam(table: String, context: Context): LiveData<List<TeamsEntity>>

}