package com.farid.proyekfootballeague.data.source.local.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMatch(
    val id: Long?,
    val eventId: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeGoalDetail: String?,
    val awayGoalDetail: String?,
    val homeLineUpGoalKeeper: String?,
    val awayLineUpGoalKeeper: String?,
    val homeLineUpDefense: String?,
    val awayLineUpDefense: String?,
    val homeLineUpMidField: String?,
    val awayLineUpMidField: String?,
    val homeLineUpForward: String?,
    val awayLineUpForward: String?,
    val homeLineUpSubstitutes: String?,
    val awayLineUpSubstitutes: String?,
    val homeShots: String?,
    val awayShots: String?,
    val dateEvent: String?,
    val time: String?,
    val homeScore: String?,
    val awayScore: String?
) : Parcelable {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val ID_HOMETEAM: String = "ID_HOMETEAM"
        const val ID_AWAYTEAM: String = "ID_AWAYTEAM"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_GOALDETAILS: String = "HOME_GOALDETAILS"
        const val AWAY_GOALDETAILS: String = "AWAY_GOALDETAILS"
        const val HOME_LINEUPGOALKEEPER: String = "HOME_LINEUPGOALKEEPER"
        const val AWAY_LINEUPGOALKEEPER: String = "AWAY_LINEUPGOALKEEPER"
        const val HOME_LINEUPDEFENSE: String = "HOME_LINEUPDEFENSE"
        const val AWAY_LINEUPDEFENSE: String = "AWAY_LINEUPDEFENSE"
        const val HOME_LINEUPFIELD: String = "HOME_LINEUPFIELD"
        const val AWAY_LINEUPFIELD: String = "AWAY_LINEUPFIELD"
        const val HOME_LINEUPFORWARD: String = "HOME_LINEUPFORWARD"
        const val AWAY_LINEUPFORWARD: String = "AWAY_LINEUPFORWARD"
        const val HOME_LINEUPSUBSTITUTES: String = "HOME_LINEUPSUBSTITUTES"
        const val AWAY_LINEUPSUBSTITUTES: String = "AWAY_LINEUPSUBSTITUTES"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME: String = "TIME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}