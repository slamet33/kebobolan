package net.slametriyadi.kebobolan.favorite.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.slametriyadi.kebobolan.db.FavoriteTeam
import net.slametriyadi.kebobolan.db.database
import net.slametriyadi.kebobolan.favorite.FavoriteTeamAdapter
import net.slametriyadi.kebobolan.match.detail.DetailMatchActivity
import net.slametriyadi.kebobolan.teams.detail.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

class TeamsFavoriteFragment : Fragment(), AnkoComponent<Context> {

    private var favoriteTeam: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var listEvent: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteTeamAdapter(context, favoriteTeam) {
            context?.startActivity<DetailTeamActivity>("nameTeam" to "${it.strTeam}")
        }

        listEvent.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favoriteTeam.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favoriteTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(this@TeamsFavoriteFragment.context as Context))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            this@TeamsFavoriteFragment.swipeRefresh = swipeRefreshLayout {
                listEvent = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
