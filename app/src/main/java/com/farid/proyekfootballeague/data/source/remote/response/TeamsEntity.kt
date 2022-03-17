package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsEntity(
    val id: Long?,
    @SerializedName("idTeam") val idTeam: String?,
    @SerializedName("strTeam") val strTeam: String?,
    @SerializedName("strSport") val strSport: String?,
    @SerializedName("strLeague") val strLeague: String?,
    @SerializedName("idLeague") val idLeague: String?,
    @SerializedName("strStadium") val strStadium: String?,
    @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
    @SerializedName("strTeamBadge") val strTeamBadge: String?
) : Parcelable