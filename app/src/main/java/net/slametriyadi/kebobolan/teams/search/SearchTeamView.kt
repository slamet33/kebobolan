package net.slametriyadi.kebobolan.teams.search

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.TeamsItem

interface SearchTeamView: BaseView {
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg: String)
    fun showQueryTeam(data: List<TeamsItem>?)
    fun showQueryNull(msg: String)
}