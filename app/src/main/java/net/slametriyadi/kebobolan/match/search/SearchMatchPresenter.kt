package net.slametriyadi.kebobolan.match.search

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.ResponseSearchMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback

class SearchMatchPresenter(var view: SearchMatchView?, var api: MatchRepository) : BasePresenter<SearchMatchView> {

    fun getSearchMatch(query: String) {
        this.view?.showLoading()
        api.getSearchMatch(query, object : RepositoryCallback<ResponseSearchMatch> {
            override fun onDataLoaded(data: ResponseSearchMatch?) {
                view?.hideLoading()
                if (data != null) {
                    if  (data.event != null) {
                        view?.showQueryMatch(data.event)
                    } else {
                        view?.showQueryNull("Data Not Found")
                    }
                }
            }

            override fun onDataError(msg: String) {
                view?.hideLoadingError(msg)
            }
        })
    }

    override fun onAttach(v: SearchMatchView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}