package net.slametriyadi.kebobolan.team

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.TeamLeagueRepository
import net.slametriyadi.kebobolan.teams.TeamsPresenter
import net.slametriyadi.kebobolan.teams.TeamsView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var api: TeamLeagueRepository

    @Mock
    private lateinit var team: ResponseTeam

    private lateinit var presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, api)
    }

    @Test
    fun getTeamTest() {
        val leagueName = "English Premier League"
        presenter.getTeam(leagueName)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getTeam(eq(leagueName), capture())
            firstValue.onDataLoaded(team)
        }

        verify(view).showTeamLeague(team.teams)
    }

    @Test
    fun getTeamErrorTest() {
        val nameTeam = "Liga 1 Indonesia"
        presenter.getTeam(nameTeam)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getTeam(eq(nameTeam), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}