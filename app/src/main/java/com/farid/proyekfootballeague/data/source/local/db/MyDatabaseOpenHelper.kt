package com.farid.proyekfootballeague.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.ID_EVENT to TEXT + UNIQUE,
            FavoriteMatch.ID_HOMETEAM to TEXT,
            FavoriteMatch.ID_AWAYTEAM to TEXT,
            FavoriteMatch.HOME_TEAM to TEXT,
            FavoriteMatch.AWAY_TEAM to TEXT,
            FavoriteMatch.HOME_GOALDETAILS to TEXT,
            FavoriteMatch.AWAY_GOALDETAILS to TEXT,
            FavoriteMatch.HOME_LINEUPGOALKEEPER to TEXT,
            FavoriteMatch.AWAY_LINEUPGOALKEEPER to TEXT,
            FavoriteMatch.HOME_LINEUPDEFENSE to TEXT,
            FavoriteMatch.AWAY_LINEUPDEFENSE to TEXT,
            FavoriteMatch.HOME_LINEUPFIELD to TEXT,
            FavoriteMatch.AWAY_LINEUPFIELD to TEXT,
            FavoriteMatch.HOME_LINEUPFORWARD to TEXT,
            FavoriteMatch.AWAY_LINEUPFORWARD to TEXT,
            FavoriteMatch.HOME_LINEUPSUBSTITUTES to TEXT,
            FavoriteMatch.AWAY_LINEUPSUBSTITUTES to TEXT,
            FavoriteMatch.HOME_SHOTS to TEXT,
            FavoriteMatch.AWAY_SHOTS to TEXT,
            FavoriteMatch.DATE_EVENT to TEXT,
            FavoriteMatch.TIME to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT
        )

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.idTeam to TEXT + UNIQUE,
            FavoriteTeam.strTeam to TEXT,
            FavoriteTeam.strSport to TEXT,
            FavoriteTeam.strLeague to TEXT,
            FavoriteTeam.idLeague to TEXT,
            FavoriteTeam.strStadium to TEXT,
            FavoriteTeam.strDescriptionEN to TEXT,
            FavoriteTeam.strTeamBadge to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)