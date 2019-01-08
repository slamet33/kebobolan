package net.slametriyadi.kebobolan.team

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import net.slametriyadi.kebobolan.teams.detail.DetailTeamPresenter
import net.slametriyadi.kebobolan.teams.detail.DetailTeamView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {
    @Mock
    private lateinit var view: DetailTeamView

    @Mock
    private lateinit var api: DetailRepository

    @Mock
    private lateinit var team: ResponseTeam

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(view, api)
    }

    @Test
    fun getDataTeamTest() {
        val nameTeam = "Arsenal"
        presenter.getDataTeam(nameTeam)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getDataTeam(eq(nameTeam), capture())
            firstValue.onDataLoaded(team)
        }

        verify(view).showTeam(team.teams)
    }

    @Test
    fun getDataEventErrorTest() {
        val nameTeam = "Persib"
        presenter.getDataTeam(nameTeam)
        argumentCaptor<RepositoryCallback<ResponseTeam>>().apply {
            verify(api).getDataTeam(eq(nameTeam), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).toastError("Error get data")
    }
}