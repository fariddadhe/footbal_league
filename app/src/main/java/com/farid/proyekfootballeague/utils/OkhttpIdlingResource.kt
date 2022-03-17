package com.farid.proyekfootballeague.utils

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient

object OkhttpIdlingResource {
    fun registerOkhttp(client: OkHttpClient) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", client))
    }
}