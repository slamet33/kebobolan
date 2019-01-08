package net.slametriyadi.kebobolan.match.next

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback

class NextPresenter(var view: NextView?, var api: MatchRepository) : BasePresenter<NextView> {

    fun getNextMatch(id: Int) {
        this.view?.showLoading()
        api.getNextMatch(id, object : RepositoryCallback<ResponseMatch> {
            override fun onDataLoaded(data: ResponseMatch?) {
                view?.hideLoading()
                if (data != null) {
                    view?.showNextMatch(data.events)
                }
            }

            override fun onDataError(msg: String) {
                view?.hideLoadingError(msg)
            }

        })
    }

    override fun onAttach(v: NextView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}
