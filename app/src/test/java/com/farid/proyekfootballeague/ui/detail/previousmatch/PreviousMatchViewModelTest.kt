package com.farid.proyekfootballeague.ui.detail.previousmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.utils.FakeData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PreviousMatchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var ligaRepository: LigaRepository = Mockito.mock(LigaRepository::class.java)

    lateinit var viewModel: PreviousMatchViewModel

    private val id = "4328"

    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = PreviousMatchViewModel(ligaRepository)
        this.viewModel.id = id
    }

    @Test
    fun getMatch() {
        val dummy: List<EventEntity> = FakeData.getMatch()
        val match: MutableLiveData<List<EventEntity>> = MutableLiveData()
        match.postValue(dummy)

        Mockito.`when`(ligaRepository.getPreviousMatch(id)).thenReturn(match)

        val observer: Observer<List<EventEntity>> = mock()

        this.viewModel.match.observeForever(observer)

        verify(observer).onChanged(dummy)
    }

}