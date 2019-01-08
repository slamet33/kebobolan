package net.slametriyadi.kebobolan.match.next

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.MatchItem

interface NextView : BaseView{
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg : String)
    fun showNextMatch(data : List<MatchItem>)
}