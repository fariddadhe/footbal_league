package com.farid.proyekfootballeague.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TableResponse(
    @SerializedName("table")
    val table: List<TableEntity>
)