package com.farid.proyekfootballeague.utils

import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.Leagues

class DataLeagues {

    fun getLeagues(): ArrayList<Leagues> {
        val leagues: ArrayList<Leagues> = arrayListOf()

        leagues.add(
            Leagues(
                4328,
                "English Premier League",
                R.drawable.english_premier_league
            )
        )

        leagues.add(
            Leagues(
                4334,
                "French Ligue 1",
                R.drawable.french_ligue_1
            )
        )

        leagues.add(
            Leagues(
                4331,
                "German Budesliga",
                R.drawable.german_bundesliga
            )
        )

        leagues.add(
            Leagues(
                4332,
                "Italian Serie A",
                R.drawable.italian_serie_a
            )
        )

        leagues.add(
            Leagues(
                4335,
                "Spanish La Liga",
                R.drawable.spanish_la_liga
            )
        )

        leagues.add(
            Leagues(
                4336,
                "American Mayor League",
                R.drawable.american_mayor_league
            )
        )

        leagues.add(
            Leagues(
                4334,
                "Protugeuese Premiera Liga",
                R.drawable.portugeuese_premiera_liga
            )
        )

        leagues.add(
            Leagues(
                4356,
                "Australian A League",
                R.drawable.australian_a_league
            )
        )

        leagues.add(
            Leagues(
                4330,
                "Scotish Premier League",
                R.drawable.scotish_premier_league
            )
        )

        leagues.add(
            Leagues(
                4396,
                "English League 1",
                R.drawable.english_league_1
            )
        )

        return leagues
    }
}