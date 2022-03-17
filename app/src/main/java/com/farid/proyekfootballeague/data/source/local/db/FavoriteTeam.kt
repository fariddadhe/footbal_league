package com.farid.proyekfootballeague.data.source.local.db

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeam(
    val id: Long?,
    @SerializedName("idTeam") val idTeam: String?,
    @SerializedName("strTeam") val strTeam: String?,
    @SerializedName("strSport") val strSport: String?,
    @SerializedName("strLeague") val strLeague: String?,
    @SerializedName("idLeague") val idLeague: String?,
    @SerializedName("strStadium") val strStadium: String?,
    @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
    @SerializedName("strTeamBadge") val strTeamBadge: String?
) : Parcelable{
    companion object{
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val idTeam: String = "idTeam"
        const val strTeam: String = "strTeam"
        const val strSport: String = "strSport"
        const val strLeague: String = "strLeague"
        const val idLeague: String = "idLeague"
        const val strStadium: String = "strStadium"
        const val strDescriptionEN: String = "strDescriptionEN"
        const val strTeamBadge: String = "strTeamBadge"
    }
}