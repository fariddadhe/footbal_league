package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("events")
    var events: List<EventEntity>
)