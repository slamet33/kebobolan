package net.slametriyadi.kebobolan.teams

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.TeamLeagueRepository

class TeamsPresenter(var view: TeamsView?, val api: TeamLeagueRepository) : BasePresenter<TeamsView> {

    fun getTeam(leagueTeam: String) {
        this.view?.showLoading()
        api.getTeam(leagueTeam, object : RepositoryCallback<ResponseTeam> {
            override fun onDataLoaded(data: ResponseTeam?) {
                view?.hideLoading()
                if (data != null) {
                    view?.showTeamLeague(data.teams)
                }
            }

            override fun onDataError(msg: String) {
                view?.hideLoadingError(msg)
            }

        })
    }

    override fun onAttach(v: TeamsView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}