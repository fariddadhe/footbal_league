package com.farid.proyekfootballeague.data.source.remote

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.farid.proyekfootballeague.data.source.api.ApiClient
import com.farid.proyekfootballeague.data.source.api.ApiInterface
import com.farid.proyekfootballeague.data.source.local.db.FavoriteMatch
import com.farid.proyekfootballeague.data.source.local.db.FavoriteTeam
import com.farid.proyekfootballeague.data.source.local.db.database
import com.farid.proyekfootballeague.data.source.remote.response.*
import com.farid.proyekfootballeague.utils.DataLeagues
import com.farid.proyekfootballeague.utils.getDate
import com.farid.proyekfootballeague.utils.getTime
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository(
    val apiInterface: ApiInterface
) {

    fun getLeagueDetail(id: String, callback: LoadLeagueDetailReceived) {
        apiInterface.getDetail(id).enqueue(object : Callback<LeaguesDetailResponse> {
            override fun onFailure(call: Call<LeaguesDetailResponse>, t: Throwable) {
                callback.onDataNotAvailable()
            }

            override fun onResponse(
                call: Call<LeaguesDetailResponse>,
                detailResponse: Response<LeaguesDetailResponse>
            ) {
                if (detailResponse.code() == 200) {
                    detailResponse.body().let {
                        callback.onLeagueDetailReceived(it!!.leagues)
                    }
                }
            }

        })

    }

    fun getLeagues(callback: LoadLeagueReceived) {
        val dataLeagues = DataLeagues()

        val data = dataLeagues.getLeagues()

        if (data.size != 0) {
            callback.onLeagueReceived(data)
        } else {
            callback.onDataNotAvailable()
        }

    }

    fun getNextMatch(id: String, callback: LoadNextMatchReceived) {
        apiInterface.getNextMatch(id)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onNextMatchReceived(it!!.events)
                        }
                    }
                }

            })
    }

    fun getPreviousMatch(id: String, callback: LoadPreviousMatchReceived) {
        apiInterface.getLastMatch(id)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onPreviousMatchReceived(it!!.events)
                        }
                    }
                }

            })
    }

    fun getDetailMatch(id: String, callback: LoadDetailMatchReceived) {
        apiInterface.getDetailMatch(id)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onDetailMatchReceived(it!!.events)
                        }
                    }
                }

            })
    }

    fun getTeams(id: String, callback: LoadTeamsReceived) {
        apiInterface.getTeams(id)
            .enqueue(object : Callback<TeamsResponse> {
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onTeamsReceived(it!!.teams)
                        }
                    }
                }

            })
    }

    fun getAllTeamLeague(id: String, callback: LoadAllTeamLeagueReceived) {
        apiInterface.getAllTeamLeague(id)
            .enqueue(object : Callback<TeamsResponse> {
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onAllTeamLeagueReceived(it!!.teams)
                        }
                    }
                }

            })
    }

    fun getTableLeague(id: String, callback: LoadTableLeagueReceived) {
        apiInterface.getTableLeague(id)
            .enqueue(object : Callback<TableResponse> {
                override fun onFailure(call: Call<TableResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                }

                override fun onResponse(
                    call: Call<TableResponse>,
                    response: Response<TableResponse>
                ) {
                    if (response.code() == 200) {
                        response.body().let {
                            callback.onTableLeagueReceived(it!!.table)
                            println(it.table.size)
                        }
                    }
                }

            })
    }

    fun getSearchMatch(e: String, callback: LoadSearchMatchReceived) {
        apiInterface.searchMatch(e)
            .enqueue(object : Callback<SearchMatchResponse> {
                override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                    callback.onDataNotAvailable(emptyList())
                }

                override fun onResponse(
                    call: Call<SearchMatchResponse>,
                    response: Response<SearchMatchResponse>
                ) {
                    if (response.body() != null) {
                        response.body().let {
                            if (it!!.event.isNullOrEmpty()) {
                                callback.onDataNotAvailable(emptyList())
                            } else {
                                val list: MutableList<EventEntity> = mutableListOf()
                                val x: List<EventEntity> = it.event
                                x.forEach {
                                    if (it.strSport == "Soccer") {
                                        list.add(it)
                                    }
                                }
                                callback.onSearchReceived(list)
                            }
                        }
                    }
                }

            })
    }

    fun getSearchTeam(t: String, callback: LoadSearchTeamReceived) {
        apiInterface.searchTeam(t)
            .enqueue(object : Callback<TeamsResponse> {
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    callback.onDataNotAvailable(emptyList())
                }

                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.body() != null) {
                        response.body().let {
                            if (it!!.teams.isNullOrEmpty()) {
                                callback.onDataNotAvailable(emptyList())
                            } else {
                                val list: MutableList<TeamsEntity> = mutableListOf()
                                val x: List<TeamsEntity> = it.teams
                                x.forEach {
                                    if (it.strSport == "Soccer") {
                                        list.add(it)
                                    }
                                }
                                callback.onSearchTeamReceived(list)
                            }
                        }
                    }
                }

            })
    }

    fun getFavoriteStateTeam(
        table: String,
        id: String,
        context: Context,
        callback: LoadFavoriteState
    ) {
        context.database.use {
            val result = select(table)
                .whereArgs(
                    "idTeam = {id}",
                    "id" to id
                )
            val favorite = result.parseList(classParser<TeamsEntity>())
            if (favorite.isNotEmpty()) {
                callback.onDataReceived(true)
            } else {
                callback.onDataNotAvailable(false)
            }
        }
    }


    fun getFavoriteState(table: String, id: String, context: Context, callback: LoadFavoriteState) {
        context.database.use {
            val result = select(table)
                .whereArgs(
                    "ID_EVENT = {id}",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) {
                callback.onDataReceived(true)
            } else {
                callback.onDataNotAvailable(false)
            }
        }
    }

    fun addFavoriteTeam(
        table: String,
        list: TeamsEntity,
        context: Context,
        callback: AddToFavorite
    ) {
        try {
            context.database.use {
                insert(
                    table,
                    FavoriteTeam.idTeam to list.idTeam,
                    FavoriteTeam.strTeam to list.strTeam,
                    FavoriteTeam.strSport to list.strSport,
                    FavoriteTeam.strLeague to list.strLeague,
                    FavoriteTeam.idLeague to list.idLeague,
                    FavoriteTeam.strStadium to list.strStadium,
                    FavoriteTeam.strDescriptionEN to list.strDescriptionEN,
                    FavoriteTeam.strTeamBadge to list.strTeamBadge
                )
                callback.onAddSuccess("Added to Favorite")
            }
        } catch (e: SQLiteConstraintException) {
            callback.onAddFailed(e.localizedMessage)
        }
    }

    fun addFavorite(
        table: String,
        list: List<EventEntity>,
        context: Context,
        callback: AddToFavorite
    ) {
        val listt = list.get(0)
        try {
            context.database.use {
                insert(
                    table,
                    FavoriteMatch.ID_EVENT to listt.idEvent,
                    FavoriteMatch.ID_HOMETEAM to listt.idHomeTeam,
                    FavoriteMatch.ID_AWAYTEAM to listt.idAwayTeam,
                    FavoriteMatch.HOME_TEAM to listt.strHomeTeam,
                    FavoriteMatch.AWAY_TEAM to listt.strAwayTeam,
                    FavoriteMatch.HOME_GOALDETAILS to listt.strHomeGoalDetails,
                    FavoriteMatch.AWAY_GOALDETAILS to listt.strAwayGoalDetails,
                    FavoriteMatch.HOME_LINEUPGOALKEEPER to listt.strHomeLineupGoalkeeper,
                    FavoriteMatch.AWAY_LINEUPGOALKEEPER to listt.strAwayLineupGoalkeeper,
                    FavoriteMatch.HOME_LINEUPDEFENSE to listt.strHomeLineupDefense,
                    FavoriteMatch.AWAY_LINEUPDEFENSE to listt.strAwayLineupDefense,
                    FavoriteMatch.HOME_LINEUPFIELD to listt.strHomeLineupMidfield,
                    FavoriteMatch.AWAY_LINEUPFIELD to listt.strAwayLineupMidfield,
                    FavoriteMatch.HOME_LINEUPFORWARD to listt.strHomeLineupForward,
                    FavoriteMatch.AWAY_LINEUPFORWARD to listt.strAwayLineupForward,
                    FavoriteMatch.HOME_LINEUPSUBSTITUTES to listt.strHomeLineupSubstitutes,
                    FavoriteMatch.AWAY_LINEUPSUBSTITUTES to listt.strAwayLineupSubstitutes,
                    FavoriteMatch.HOME_SHOTS to listt.intHomeShots,
                    FavoriteMatch.AWAY_SHOTS to listt.intAwayShots,
                    FavoriteMatch.DATE_EVENT to getDate(listt.dateEvent),
                    FavoriteMatch.TIME to getTime(listt.strTime),
                    FavoriteMatch.HOME_SCORE to listt.intHomeScore,
                    FavoriteMatch.AWAY_SCORE to listt.intAwayScore
                )
                callback.onAddSuccess("Added to Favorite")
            }
        } catch (e: SQLiteConstraintException) {
            callback.onAddFailed(e.localizedMessage)
        }
    }

    fun removeFavoriteTeam(table: String, id: String, context: Context, callback: RemoveFavorite) {
        try {
            context.database.use {
                delete(
                    table, "idTeam = {id}",
                    "id" to id
                )
            }
            callback.onRemoveSuccess("Removed from Favorite")
        } catch (e: SQLiteConstraintException) {
            callback.onRemoveFailed(e.localizedMessage)
        }
    }

    fun removeFavorite(table: String, id: String, context: Context, callback: RemoveFavorite) {
        try {
            context.database.use {
                delete(
                    table, "ID_EVENT = {id}",
                    "id" to id
                )
            }
            callback.onRemoveSuccess("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            callback.onRemoveFailed(e.localizedMessage)
        }
    }

    fun favoriteTeam(table: String, context: Context, callback: LoadFavoriteTeamReceived) {
        context.database.use {
            val result = select(table)
            val favorite = result.parseList(classParser<TeamsEntity>())
            favorite.let {
                if (it.isNotEmpty()) {
                    callback.onDataReceived(it)
                } else {
                    callback.onDataNotAvailable(emptyList())
                }
            }
        }
    }

    fun favoriteMatch(
        table: String,
        event: String,
        context: Context,
        callback: LoadFavoriteMatchReceived
    ) {
        context.database.use {
            val list: MutableList<FavoriteMatch> = mutableListOf()
            val result = select(table)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorite.forEach {
                if (event.equals("next")) {
                    if (it.awayLineUpGoalKeeper == "") {
                        list.add(it)
                    }
                } else {
                    if (it.awayLineUpGoalKeeper != "") {
                        list.add(it)
                    }
                }
            }
            if (list.isNotEmpty())
                callback.onDataReceived(list)
            else
                callback.onDataNotAvailable(emptyList())
        }
    }

    interface LoadLeagueDetailReceived {
        fun onLeagueDetailReceived(leaguesDetailResponse: List<LeaguesDetail>)

        fun onDataNotAvailable()
    }

    interface LoadLeagueReceived {
        fun onLeagueReceived(leagues: List<Leagues>?)

        fun onDataNotAvailable()
    }

    interface LoadNextMatchReceived {
        fun onNextMatchReceived(match: List<EventEntity>)

        fun onDataNotAvailable()
    }

    interface LoadPreviousMatchReceived {
        fun onPreviousMatchReceived(match: List<EventEntity>)

        fun onDataNotAvailable()
    }

    interface LoadDetailMatchReceived {
        fun onDetailMatchReceived(match: List<EventEntity>)

        fun onDataNotAvailable()
    }

    interface LoadTeamsReceived {
        fun onTeamsReceived(teams: List<TeamsEntity>)

        fun onDataNotAvailable()
    }

    interface LoadSearchMatchReceived {
        fun onSearchReceived(match: List<EventEntity>)

        fun onDataNotAvailable(match: List<EventEntity>)
    }

    interface LoadAllTeamLeagueReceived {
        fun onAllTeamLeagueReceived(team: List<TeamsEntity>)

        fun onDataNotAvailable()
    }

    interface LoadTableLeagueReceived {
        fun onTableLeagueReceived(table: List<TableEntity>)

        fun onDataNotAvailable()
    }

    interface LoadSearchTeamReceived {
        fun onSearchTeamReceived(team: List<TeamsEntity>)

        fun onDataNotAvailable(team: List<TeamsEntity>)
    }


    interface LoadFavoriteState {
        fun onDataReceived(boolean: Boolean)

        fun onDataNotAvailable(boolean: Boolean)
    }

    interface AddToFavorite {
        fun onAddSuccess(message: String)

        fun onAddFailed(message: String)
    }

    interface RemoveFavorite {
        fun onRemoveSuccess(message: String)

        fun onRemoveFailed(message: String)
    }


    interface LoadFavoriteMatchReceived {
        fun onDataReceived(match: List<FavoriteMatch>)

        fun onDataNotAvailable(match: List<FavoriteMatch>)
    }

    interface LoadFavoriteTeamReceived {
        fun onDataReceived(team: List<TeamsEntity>)

        fun onDataNotAvailable(team: List<TeamsEntity>)
    }

    companion object {
        private var INSTANCE: RemoteRepository? = null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: RemoteRepository(ApiClient.getClient().create(ApiInterface::class.java))
    }
}