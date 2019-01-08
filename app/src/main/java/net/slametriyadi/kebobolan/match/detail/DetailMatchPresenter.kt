package net.slametriyadi.kebobolan.match.detail

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.MatchItem
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.detail.DetailRepository

class DetailMatchPresenter(var view: DetailMatchView?, var api: DetailRepository) : BasePresenter<DetailMatchView> {

    fun getDataEvent(eventId: String?) {
        view?.showLoading()
        if (eventId != null) {
            api.getDataEvent(eventId, object : RepositoryCallback<ResponseMatch> {
                override fun onDataError(msg: String) {
                    view?.hideLoadingError(msg)
                }

                override fun onDataLoaded(data: ResponseMatch?) {
                    val dataMatch: List<MatchItem>? = data?.events
                    if (dataMatch != null) {
                        view?.showTeam(dataMatch)
                    }
                }
            })
        }
    }

    fun getDataHomeTeams(nameTeam: String?) {
        if (nameTeam != null) {
            api.getDataHomeTeams(nameTeam, object : RepositoryCallback<ResponseTeam> {
                override fun onDataLoaded(data: ResponseTeam?) {
                    val dataHome: List<TeamsItem>? = data?.teams
                    if (dataHome != null) {
                        view?.showTeamHome(dataHome)
                    }
                }

                override fun onDataError(msg: String) {
                    view?.hideLoadingError(msg)
                }

            })
        }
    }

    fun getDataAwayTeams(nameTeam: String?) {
        if (nameTeam != null) {
            api.getDataAwayTeams(nameTeam, object : RepositoryCallback<ResponseTeam> {
                override fun onDataLoaded(data: ResponseTeam?) {
                    val dataAway: List<TeamsItem>? = data?.teams
                    if (dataAway != null) {
                        view?.hideLoading()
                        view?.showTeamAway(dataAway)
                    }
                }

                override fun onDataError(msg: String) {
                    view?.hideLoadingError(msg)
                }

            })
        }
    }

    override fun onAttach(v: DetailMatchView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}
