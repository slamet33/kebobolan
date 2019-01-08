package net.slametriyadi.kebobolan.repository.detail

import net.slametriyadi.kebobolan.api.ConfigRetrofit
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.model.ResponseTeam
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {

    fun getDataEvent(eventId: String, callback: RepositoryCallback<ResponseMatch>) {
        val id : Int = eventId.toInt()
        ConfigRetrofit.service.getEvent(id).enqueue(object : Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseMatch>, response: Response<ResponseMatch>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get data")
                    }
                }
            }
        })
    }

    fun getDataTeam (nameTeams: String?, callback: RepositoryCallback<ResponseTeam>) {
        ConfigRetrofit.service.getTeam(nameTeams.toString()).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get data")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

        })
    }

    fun getDataHomeTeams(nameTeams: String?, callback: RepositoryCallback<ResponseTeam>) {
        ConfigRetrofit.service.getTeam(nameTeams.toString()).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get data")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

        })
    }

    fun getDataAwayTeams(nameTeams: String?, callback: RepositoryCallback<ResponseTeam>) {
        ConfigRetrofit.service.getTeam(nameTeams.toString()).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get Data")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

        })
    }
}