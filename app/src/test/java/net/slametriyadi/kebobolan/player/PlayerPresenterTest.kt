package net.slametriyadi.kebobolan.player

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.players.PlayerPresenter
import net.slametriyadi.kebobolan.players.PlayerView
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.players.TeamPlayersRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {
    @Mock
    private lateinit var view: PlayerView

    @Mock
    private lateinit var api: TeamPlayersRepository

    @Mock
    private lateinit var players: ResponsePlayers

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, api)
    }

    @Test
    fun getTeamPlayerTest() {
        val nameTeam = "Arsenal"
        presenter.getTeamPlayer(nameTeam)
        argumentCaptor<RepositoryCallback<ResponsePlayers>>().apply {
            verify(api).getDataTeamPlayers(eq(nameTeam), capture())
            firstValue.onDataLoaded(players)
        }

        verify(view).showDataPlayers(players.player)
    }

    @Test
    fun getTeamPlayerErrorTest() {
        val nameTeam = "Persib"
        presenter.getTeamPlayer(nameTeam)
        argumentCaptor<RepositoryCallback<ResponsePlayers>>().apply {
            verify(api).getDataTeamPlayers(eq(nameTeam), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).toastError("Error get data")
    }
}