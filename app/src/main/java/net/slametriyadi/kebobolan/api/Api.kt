package net.slametriyadi.kebobolan.api

import net.slametriyadi.kebobolan.model.ResponseMatch
import net.slametriyadi.kebobolan.model.ResponsePlayers
import net.slametriyadi.kebobolan.model.ResponseSearchMatch
import net.slametriyadi.kebobolan.model.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("api/v1/json/1/eventspastleague.php")
    fun getLastMatch(
        @Query("id") id: Int
    ): Call<ResponseMatch>

    @GET("api/v1/json/1/searchevents.php")
    fun searchEvents(
        @Query("e") query: String
    ): Call<ResponseSearchMatch>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(
        @Query("id") id: Int
    ): Call<ResponseMatch>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEvent(
        @Query("id") id: Int
    ): retrofit2.Call<ResponseMatch>

    @GET("api/v1/json/1/searchteams.php")
    fun getTeam(
        @Query("t") id: String
    ): retrofit2.Call<ResponseTeam>

    @GET("api/v1/json/1/search_all_teams.php")
    fun getTeamLeague(
        @Query("l") id: String
    ): retrofit2.Call<ResponseTeam>

    @GET("api/v1/json/1/searchplayers.php")
    fun getTeamPlayer(
        @Query("t") nameTeam: String
    ): Call<ResponsePlayers>

    @GET("api/v1/json/1/searchplayers.php?p=Danny%20Welbeck")
    fun getPlayerDetail(
        @Query("p") namePlayer: String
    ): Call<ResponsePlayers>
}