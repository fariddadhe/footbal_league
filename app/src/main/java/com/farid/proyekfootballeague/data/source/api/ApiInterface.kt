package com.farid.proyekfootballeague.data.source.api

import com.farid.proyekfootballeague.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: String): Call<EventResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String): Call<EventResponse>

    @GET("lookupleague.php")
    fun getDetail(@Query("id") id: String): Call<LeaguesDetailResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id: String): Call<EventResponse>

    @GET("lookupteam.php")
    fun getTeams(@Query("id") id: String): Call<TeamsResponse>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") e: String): Call<SearchMatchResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeamLeague(@Query ("id")id: String): Call<TeamsResponse>

    @GET("lookuptable.php")
    fun getTableLeague(@Query("l")l: String): Call<TableResponse>

    @GET("searchteams.php")
    fun searchTeam(@Query("t")t: String): Call<TeamsResponse>
}