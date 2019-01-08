package net.slametriyadi.kebobolan.match.last

import net.slametriyadi.kebobolan.base.BaseView
import net.slametriyadi.kebobolan.model.MatchItem

interface LastView : BaseView{
    fun showLoading()
    fun hideLoading()
    fun hideLoadingError(msg : String)
    fun showLastMatch(data : List<MatchItem>)
}