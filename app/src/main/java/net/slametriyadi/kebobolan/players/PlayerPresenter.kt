package net.slametriyadi.kebobolan.players

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.PlayerItem
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.players.TeamPlayersRepository

class PlayerPresenter(var view: PlayerView?, var api: TeamPlayersRepository) : BasePresenter<PlayerView> {

    fun getTeamPlayer(nameTeam: String?) {
        if (nameTeam != null) {
            api.getDataTeamPlayers(nameTeam, object : RepositoryCallback<ResponsePlayers> {
                override fun onDataError(msg: String) {
                    view?.toastError(msg)
                }

                override fun onDataLoaded(data: ResponsePlayers?) {
                    val dataPlayers: List<PlayerItem>? = data?.player
                    if (dataPlayers != null) {
                        view?.showDataPlayers(dataPlayers)
                    }
                }
            })
        }
    }

    override fun onAttach(v: PlayerView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}