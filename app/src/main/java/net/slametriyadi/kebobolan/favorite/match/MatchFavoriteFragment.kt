package net.slametriyadi.kebobolan.favorite.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.slametriyadi.kebobolan.db.FavoriteMatch
import net.slametriyadi.kebobolan.db.database
import net.slametriyadi.kebobolan.favorite.FavoriteMatchAdapter
import net.slametriyadi.kebobolan.match.detail.DetailMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFavoriteFragment : Fragment(), AnkoComponent<Context>{

    private var favoriteMatches: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var listEvent: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteMatchAdapter(favoriteMatches) {
            context?.startActivity<DetailMatchActivity>("idEvent" to "${it.eventId}")
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
        favoriteMatches.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_MATCH_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favoriteMatches.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(this@MatchFavoriteFragment.context as Context))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            this@MatchFavoriteFragment.swipeRefresh = swipeRefreshLayout {
                listEvent = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}