package net.slametriyadi.kebobolan.repository

import net.slametriyadi.kebobolan.api.ConfigRetrofit
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponseTeam
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamLeagueRepository {
    fun getTeam(leagueName: String, callback: RepositoryCallback<ResponseTeam>) {
        ConfigRetrofit.service.getTeamLeague(leagueName).enqueue(object : Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get Data")
                    }
                }
            }
        })
    }

    fun getSearchTeam(query: String, callback: RepositoryCallback<ResponseTeam>) {
        ConfigRetrofit.service.getTeam(query).enqueue(object : Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get Data")
                    }
                }
            }
        })
    }
}