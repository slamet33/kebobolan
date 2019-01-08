package net.slametriyadi.kebobolan.repository

import net.slametriyadi.kebobolan.api.ConfigRetrofit
import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponseSearchMatch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {
    fun getLastMatch(id: Int, callback: RepositoryCallback<ResponseMatch>) {
        ConfigRetrofit.service.getLastMatch(id).enqueue(object : Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseMatch>, response: Response<ResponseMatch>) {
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

    fun getNextMatch(id: Int, callback: RepositoryCallback<ResponseMatch>) {
        ConfigRetrofit.service.getNextMatch(id).enqueue(object : Callback<ResponseMatch> {
            override fun onFailure(call: Call<ResponseMatch>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseMatch>, response: Response<ResponseMatch>) {
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

    fun getSearchMatch(query: String, callback: RepositoryCallback<ResponseSearchMatch>) {
        ConfigRetrofit.service.searchEvents(query).enqueue(object : Callback<ResponseSearchMatch> {
            override fun onFailure(call: Call<ResponseSearchMatch>, t: Throwable) {
                callback.onDataError("Api Failure")
            }

            override fun onResponse(call: Call<ResponseSearchMatch>, response: Response<ResponseSearchMatch>) {
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