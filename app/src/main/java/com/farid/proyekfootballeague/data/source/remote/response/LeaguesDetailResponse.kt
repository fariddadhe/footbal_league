package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LeaguesDetailResponse(
    @SerializedName("leagues")
    val leagues: List<LeaguesDetail>
)