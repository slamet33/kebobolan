package net.slametriyadi.kebobolan.match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.match.last.LastPresenter
import net.slametriyadi.kebobolan.match.last.LastView
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LastPresenterTest {
    @Mock
    private lateinit var view: LastView

    @Mock
    private lateinit var api: MatchRepository

    @Mock
    private lateinit var match: ResponseMatch

    private lateinit var presenter: LastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastPresenter(view, api)
    }

    @Test
    fun getLastMatchTest() {
        val id = 4328
        presenter.getLastMatch(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getLastMatch(eq(id), capture())
            firstValue.onDataLoaded(match)
        }

        verify(view).showLastMatch(match.events)
    }

    @Test
    fun getLastMatchErrorTestDAWDAWDAWDAWDAWD() {
        val id = 2
        presenter.getLastMatch(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getLastMatch(eq(id), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}