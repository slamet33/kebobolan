package net.slametriyadi.kebobolan.teams.detail

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import net.slametriyadi.kebobolan.repository.RepositoryCallback

class DetailTeamPresenter(var view: DetailTeamView?, var api: DetailRepository) : BasePresenter<DetailTeamView> {

    fun getDataTeam(nameTeam: String?) {
        if (nameTeam != null) {
            api.getDataTeam(nameTeam, object : RepositoryCallback<ResponseTeam> {
                override fun onDataError(msg: String) {
                    view?.toastError(msg)
                }

                override fun onDataLoaded(data: ResponseTeam?) {
                    val dataTeam: List<TeamsItem>? = data?.teams
                    if (dataTeam != null) {
                        view?.showTeam(dataTeam)
                    }
                }
            })
        }
    }

    override fun onAttach(v: DetailTeamView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}
