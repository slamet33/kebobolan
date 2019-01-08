package net.slametriyadi.kebobolan.players.detail

import net.slametriyadi.kebobolan.base.BasePresenter
import net.slametriyadi.kebobolan.model.PlayerItem
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import net.slametriyadi.kebobolan.repository.players.PlayerDetailRepository

class DetailPlayerPresenter(var view: DetailPlayerView?, var api: PlayerDetailRepository) :
    BasePresenter<DetailPlayerView> {

    fun getDataPlayer(namePlayer: String) {
        api.getDataPlayers(namePlayer, object : RepositoryCallback<ResponsePlayers> {
            override fun onDataLoaded(data: ResponsePlayers?) {
                val dataPlayers: List<PlayerItem>? = data?.player
                if (dataPlayers != null) {
                    view?.showDataPlayers(dataPlayers)
                }
            }

            override fun onDataError(msg: String) {
                view?.toastError(msg)
            }
        })
    }

    override fun onAttach(v: DetailPlayerView) {
        view = v
    }

    override fun onDettach() {
        view = null
    }

}