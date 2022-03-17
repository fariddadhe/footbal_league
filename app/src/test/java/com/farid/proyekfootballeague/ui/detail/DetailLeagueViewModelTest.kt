package com.farid.proyekfootballeague.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.LeaguesDetail
import com.farid.proyekfootballeague.utils.FakeData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeagueViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var ligaRepository: LigaRepository = Mockito.mock(LigaRepository::class.java)

    lateinit var viewModel: DetailLeagueViewModel

    private val id = "4328"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = DetailLeagueViewModel(ligaRepository)
        this.viewModel.id = id
    }

    @Test
    fun getLeague() {
        val dummy: List<LeaguesDetail> = FakeData.getDetailLeague()
        val league: MutableLiveData<List<LeaguesDetail>> = MutableLiveData()
        league.postValue(dummy)

        Mockito.`when`(ligaRepository.getDetailLeague(id)).thenReturn(league)
        val observer: Observer<List<LeaguesDetail>> = mock()

        this.viewModel.detail.observeForever(observer)

        verify(observer).onChanged(dummy)
    }
}