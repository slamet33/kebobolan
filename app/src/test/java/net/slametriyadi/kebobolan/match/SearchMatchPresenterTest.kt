package net.slametriyadi.kebobolan.match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.match.search.SearchMatchPresenter
import net.slametriyadi.kebobolan.match.search.SearchMatchView
import net.slametriyadi.kebobolan.model.ResponseSearchMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var api: MatchRepository

    @Mock
    private lateinit var match: ResponseSearchMatch

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, api)
    }

    @Test
    fun getSearchMatchTest() {
        val query = "Man U"
        presenter.getSearchMatch(query)
        argumentCaptor<RepositoryCallback<ResponseSearchMatch>>().apply {
            verify(api).getSearchMatch(eq(query), capture())
            firstValue.onDataLoaded(match)
        }

        verify(view).showQueryMatch(match.event)
    }

    @Test
    fun getDataSearchMatchErrorTest() {
        val query = "Persib"
        presenter.getSearchMatch(query)
        argumentCaptor<RepositoryCallback<ResponseSearchMatch>>().apply {
            verify(api).getSearchMatch(eq(query), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}