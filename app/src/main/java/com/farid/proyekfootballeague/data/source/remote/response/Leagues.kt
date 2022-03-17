package com.farid.proyekfootballeague.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Leagues(
    val id: Int,
    val name: String,
    val logo: Int
) : Parcelable