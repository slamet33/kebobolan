package net.slametriyadi.kebobolan.teams.search

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.TeamLeagueRepository

class SearchTeamPresenter(var view: SearchTeamView?, val api: TeamLeagueRepository) : BasePresenter<SearchTeamView> {

    fun getSearchTeam(query: String) {
        view?.showLoading()
        api.getSearchTeam(query, object : RepositoryCallback<ResponseTeam> {
            override fun onDataLoaded(data: ResponseTeam?) {
                if (data != null) {
                    view?.hideLoading()
                    if (data.teams != null) {
                        view?.showQueryTeam(data.teams)
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

    override fun onAttach(v: SearchTeamView) {
        this.view = v
    }

    override fun onDettach() {
        view = null
    }

}