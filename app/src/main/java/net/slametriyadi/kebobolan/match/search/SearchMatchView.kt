package net.slametriyadi.kebobolan.match.search

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.EventItem

interface SearchMatchView : BaseView {
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg: String)
    fun showQueryMatch(dataMatch: List<EventItem>?)
    fun showQueryNull(msg: String)
}
