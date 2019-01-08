package net.slametriyadi.kebobolan.match

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import net.slametriyadi.kebobolan.match.detail.DetailMatchPresenter
import net.slametriyadi.kebobolan.match.detail.DetailMatchView
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var api: DetailRepository

    @Mock
    private lateinit var match: ResponseMatch

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, api)
    }

    @Test
    fun getDataEventTest() {
        val id = "576606"
        presenter.getDataEvent(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getDataEvent(eq(id), capture())
            firstValue.onDataLoaded(match)
        }
        verify(view).showLoading()
        verify(view).showTeam(match.events)
    }

    @Test
    fun getDataEventErrorTest() {
        val id = "2"
        presenter.getDataEvent(id)
        argumentCaptor<RepositoryCallback<ResponseMatch>>().apply {
            verify(api).getDataEvent(eq(id), capture())
            firstValue.onDataError("Error get data")
        }
        verify(view).hideLoadingError("Error get data")
    }
}
