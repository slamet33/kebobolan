package net.slametriyadi.kebobolan.repository.players

import net.slametriyadi.kebobolan.api.ConfigRetrofit
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.repository.RepositoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPlayersRepository {
    fun getDataTeamPlayers(nameTeams: String?, callback: RepositoryCallback<ResponsePlayers>) {
        ConfigRetrofit.service.getTeamPlayer(nameTeams.toString()).enqueue(object : Callback<ResponsePlayers> {
            override fun onResponse(call: Call<ResponsePlayers>, response: Response<ResponsePlayers>) {
                response.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    } else {
                        callback.onDataError("Error get Data")
                    }
                }
            }

            override fun onFailure(call: Call<ResponsePlayers>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

        })
    }
}