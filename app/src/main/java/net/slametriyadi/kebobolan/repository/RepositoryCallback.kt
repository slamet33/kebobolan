package net.slametriyadi.kebobolan.repository

interface RepositoryCallback <T>{
    fun onDataLoaded(data: T?)
    fun onDataError(msg : String)
}