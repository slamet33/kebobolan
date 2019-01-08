package net.slametriyadi.kebobolan.match.detail

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.MatchItem
import net.slametriyadi.kebobolan.model.TeamsItem

interface DetailMatchView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg: String)
    fun showTeam(dataMatch: List<MatchItem>)
    fun showTeamHome(dataTeamsHome: List<TeamsItem>)
    fun showTeamAway(dataTeamsAway: List<TeamsItem>)
}