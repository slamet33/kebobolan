package net.slametriyadi.kebobolan.team

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.TeamLeagueRepository
import net.slametriyadi.kebobolan.teams.search.SearchTeamPresenter
import net.slametriyadi.kebobolan.teams.search.SearchTeamView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {
    @Mock
    private lateinit var view: SearchTeamView

    @Mock
    private lateinit var api: TeamLeagueRepository

    @Mock
    private lateinit var team: ResponseTeam

    private lateinit var presenter: SearchTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(view, api)
    }

    @Test
    fun getSearchTeamTest() {
        val nameTeam = "Arsenal"
        presenter.getSearchTeam(nameTeam)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getSearchTeam(eq(nameTeam), capture())
            firstValue.onDataLoaded(team)
        }

        verify(view).showQueryTeam(team.teams)
    }

    @Test
    fun getSearchTeamTestError() {
        val nameTeam = "Persib"
        presenter.getSearchTeam(nameTeam)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getSearchTeam(eq(nameTeam), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}