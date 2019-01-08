package net.slametriyadi.kebobolan.players

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.PlayerItem

interface PlayerView : BaseView {
    fun toastError(msg: String)
    fun showDataPlayers(dataPlayers: List<PlayerItem>)
}