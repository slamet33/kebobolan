package net.slametriyadi.kebobolan.teams.detail.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_players.*

import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.PlayerItem
import net.slametriyadi.kebobolan.players.DetailPlayerAdapter
import net.slametriyadi.kebobolan.players.PlayerPresenter
import net.slametriyadi.kebobolan.players.PlayerView
import net.slametriyadi.kebobolan.repository.players.TeamPlayersRepository
import org.jetbrains.anko.support.v4.toast

class PlayersFragment : Fragment(), PlayerView {

    private lateinit var nameTeam: String
    private lateinit var detailPresenter: PlayerPresenter
    private var teams: MutableList<PlayerItem> = mutableListOf()
    private lateinit var adapter: DetailPlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onStart() {
        super.onStart()
        detailPresenter = PlayerPresenter(this, TeamPlayersRepository())
        onAttachView()
    }

    override fun toastError(msg: String) {
        toast(msg)
    }

    override fun showDataPlayers(dataPlayers: List<PlayerItem>) {
        teams.clear()
        teams.addAll(dataPlayers)
        adapter = DetailPlayerAdapter(dataPlayers, context)
        list_player.layoutManager = LinearLayoutManager(context)
        list_player.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onAttachView() {
        if (getArguments() != null) {
            nameTeam = getArguments()?.getString("nameTeam")!!
        }
        detailPresenter.onAttach(this@PlayersFragment)
        detailPresenter.getTeamPlayer(nameTeam)
    }

    override fun onDettachView() {
        detailPresenter.onDettach()
    }
}
