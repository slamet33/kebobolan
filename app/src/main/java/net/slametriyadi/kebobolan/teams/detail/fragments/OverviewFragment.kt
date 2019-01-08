package net.slametriyadi.kebobolan.teams.detail.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_overview.*

import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import net.slametriyadi.kebobolan.teams.detail.DetailTeamPresenter
import net.slametriyadi.kebobolan.teams.detail.DetailTeamView
import org.jetbrains.anko.support.v4.toast

class OverviewFragment : Fragment(), DetailTeamView {

    private lateinit var nameTeam: String
    private lateinit var detailPresenter: DetailTeamPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onStart() {
        super.onStart()
        detailPresenter = DetailTeamPresenter(this, DetailRepository())
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun toastError(msg: String) {
        toast(msg)
    }

    override fun showTeam(dataTeam: List<TeamsItem>?) {
        detailDescriptionTeam.text = dataTeam?.get(0)?.strDescriptionEN
    }

    override fun onAttachView() {
        if (getArguments() != null) {
            nameTeam = getArguments()?.getString("nameTeam")!!
        }
        detailPresenter.onAttach(this@OverviewFragment)
        detailPresenter.getDataTeam(nameTeam)
    }

    override fun onDettachView() {
        detailPresenter.onDettach()
    }


}
