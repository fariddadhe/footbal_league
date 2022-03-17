package com.farid.proyekfootballeague.di

import android.content.Context
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.RemoteRepository

object Injection {
    fun provideRepository(context: Context) =
        LigaRepository.getInstance(RemoteRepository.getInstance())
}