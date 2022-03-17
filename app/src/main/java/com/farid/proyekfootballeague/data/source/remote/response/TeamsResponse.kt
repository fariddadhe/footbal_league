package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("teams")
    val teams: List<TeamsEntity>
)