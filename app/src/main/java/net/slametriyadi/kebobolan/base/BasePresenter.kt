package net.slametriyadi.kebobolan.base

interface BasePresenter<T : BaseView> {
    fun onAttach(v: T)
    fun onDettach()
}