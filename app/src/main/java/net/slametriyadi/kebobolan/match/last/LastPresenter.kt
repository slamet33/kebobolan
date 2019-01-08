package net.slametriyadi.kebobolan.match.last

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.match.next.NextView
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback

class LastPresenter(var view: LastView?, var api: MatchRepository) : BasePresenter<LastView> {

    fun getLastMatch(id: Int) {
        this.view?.showLoading()
        api.getLastMatch(id, object : RepositoryCallback<ResponseMatch> {
            override fun onDataLoaded(data: ResponseMatch?) {
                view?.hideLoading()
                if (data != null) {
                    view?.showLastMatch(data.events)
                }
            }

            override fun onDataError(msg: String) {
                view?.hideLoadingError(msg)
            }

        })
    }

    override fun onAttach(v: LastView) {
        this.view = v
    }

    override fun onDettach() {
        view = null
    }

}