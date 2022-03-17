package com.farid.proyekfootballeague.ui.searchmatch

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

class SearchMatchViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var ligaRepository: LigaRepository = Mockito.mock(LigaRepository::class.java)

    lateinit var viewModel: SearchMatchViewModel

    private val query = "arsenal"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.viewModel = SearchMatchViewModel(this.ligaRepository)
        this.viewModel.query = query
    }

    @Test
    fun getSearch() {
        val dummy: List<EventEntity> = FakeData.getSearchMatch()
        val search: MutableLiveData<List<EventEntity>> = MutableLiveData()
        search.postValue(dummy)

        Mockito.`when`(ligaRepository.getSearchMatch(query)).thenReturn(search)
        val observer: Observer<List<EventEntity>> = mock()

        this.viewModel.search.observeForever(observer)

        verify(observer).onChanged(dummy)
    }

}