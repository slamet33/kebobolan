package net.slametriyadi.kebobolan.teams.detail

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.TeamsItem

interface DetailTeamView : BaseView {
    fun toastError(msg: String)
    fun showTeam(dataTeam: List<TeamsItem>?)
}