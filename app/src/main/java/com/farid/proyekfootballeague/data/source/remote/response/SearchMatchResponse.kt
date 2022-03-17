package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val event: List<EventEntity>
)