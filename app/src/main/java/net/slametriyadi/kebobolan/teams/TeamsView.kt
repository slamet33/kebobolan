package net.slametriyadi.kebobolan.teams

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.TeamsItem


interface TeamsView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg : String)
    fun showTeamLeague(data: List<TeamsItem>?)
}