package net.slametriyadi.kebobolan.players.detail

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.PlayerItem

interface DetailPlayerView : BaseView {
    fun toastError(msg: String)
    fun showDataPlayers(dataPlayers: List<PlayerItem>)
}