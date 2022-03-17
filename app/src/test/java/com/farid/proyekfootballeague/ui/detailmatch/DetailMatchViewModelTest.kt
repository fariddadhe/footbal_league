package com.farid.proyekfootballeague.ui.detailmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.farid.proyekfootballeague.data.source.LigaRepository
import com.farid.proyekfootballeague.data.source.remote.response.EventEntity
import com.farid.proyekfootballeague.data.source.remote.response.TeamsEntity
import com.farid.proyekfootballeague.utils.FakeData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailMatchViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var ligaRepository: LigaRepository = Mockito.mock(LigaRepository::class.java)

    lateinit var viewModel: DetailMatchViewModel

    val dummy: List<EventEntity> = FakeData.getDetailMatch()
    private val idTeam = dummy.get(0).idAwayTeam.toString()
    private val id = "602269"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = DetailMatchViewModel(this.ligaRepository)
        this.viewModel.id = id
        this.viewModel.idTeams = idTeam
    }

    @Test
    fun getDetailMatch() {
        val match: MutableLiveData<List<EventEntity>> = MutableLiveData()
        match.postValue(dummy)

        `when`(ligaRepository.getDetailMatch(id)).thenReturn(match)
        val observer: Observer<List<EventEntity>> = mock()

        this.viewModel.detail.observeForever(observer)

        verify(observer).onChanged(dummy)
    }

    @Test
    fun getDetailTeam() {
        val dummyTeam: List<TeamsEntity> = FakeData.getDetailTeams()
        val teams: MutableLiveData<List<TeamsEntity>> = MutableLiveData()
        teams.postValue(dummyTeam)

        `when`(ligaRepository.getTeams(idTeam)).thenReturn(teams)
        val observer: Observer<List<TeamsEntity>> = mock()

        this.viewModel.teams.observeForever(observer)

        verify(observer).onChanged(dummyTeam)
    }
}