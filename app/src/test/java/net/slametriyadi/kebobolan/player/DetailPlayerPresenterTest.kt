package net.slametriyadi.kebobolan.player

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.players.detail.DetailPlayerPresenter
import net.slametriyadi.kebobolan.players.detail.DetailPlayerView
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.players.PlayerDetailRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailPlayerPresenterTest {
    @Mock
    private lateinit var view: DetailPlayerView

    @Mock
    private lateinit var api: PlayerDetailRepository

    @Mock
    private lateinit var players: ResponsePlayers

    private lateinit var presenter: DetailPlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPlayerPresenter(view, api)
    }

    @Test
    fun getDataPlayerTest() {
        val namePlayer = "Paul Pogba"
        presenter.getDataPlayer(namePlayer)
        argumentCaptor<RepositoryCallback<ResponsePlayers>>().apply {
            verify(api).getDataPlayers(eq(namePlayer), capture())
            firstValue.onDataLoaded(players)
        }

        verify(view).showDataPlayers(players.player)
    }

    @Test
    fun getDataPlayerErrorTest() {
        val nameTeam = "Widodo"
        presenter.getDataPlayer(nameTeam)
        argumentCaptor<RepositoryCallback<ResponsePlayers>>().apply {
            verify(api).getDataPlayers(eq(nameTeam), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).toastError("Error get data")
    }
}