package com.farid.proyekfootballeague.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun getDate(date: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val conv = SimpleDateFormat("EEE, dd MMM yyy")
    var t: Date? = null
    try {
        t = dateFormat.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return conv.format(t)
}

@SuppressLint("SimpleDateFormat")
fun getTime(times: String): String? {
    val timeFormat = SimpleDateFormat("HH:mm")
    val conv = SimpleDateFormat("hh:mm a")
    var t: Date? = null
    try {
        t = timeFormat.parse(times)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return conv.format(t)
}