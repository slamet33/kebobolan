package net.slametriyadi.kebobolan.match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.match.next.NextPresenter
import net.slametriyadi.kebobolan.match.next.NextView
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NextPresenterTest {

    @Mock
    private lateinit var view: NextView

    @Mock
    private lateinit var api: MatchRepository

    @Mock
    private lateinit var match: ResponseMatch

    private lateinit var presenter: NextPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view, api)
    }

    @Test
    fun getNextMatchTest() {
        val id = 4328
        presenter.getNextMatch(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getNextMatch(eq(id), capture())
            firstValue.onDataLoaded(match)
        }

        verify(view).showNextMatch(match.events)
    }

    @Test
    fun getNextMatchErrorTest() {
        val id = 2
        presenter.getNextMatch(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getNextMatch(eq(id), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}