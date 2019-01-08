package net.slametriyadi.kebobolan.match.search

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SearchView
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.EventItem
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.utils.invisible
import net.slametriyadi.kebobolan.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {

    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mListMatch: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var presenter: SearchMatchPresenter
    lateinit var query: String
    private var events: MutableList<EventItem> = mutableListOf()
    lateinit var adapter: SearchViewAdapter
    private lateinit var searchItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            mSwipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    mListMatch = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        query = intent.getStringExtra("query")
        presenter = SearchMatchPresenter(this, MatchRepository())
        onAttachView()
        title = query

    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun hideLoadingError(msg: String) {
        toast(msg)
        progressBar.invisible()
    }

    override fun showQueryMatch(dataMatch: List<EventItem>?) {
        mSwipeRefresh.isRefreshing = false
        events.clear()
        if (dataMatch != null) {
            events.addAll(dataMatch)
        }
        adapter = SearchViewAdapter(dataMatch, this)
        mListMatch.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showQueryNull(msg: String) {
        toast(msg)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
        presenter.getSearchMatch(query)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        if (menu != null) {
            searchItem = menu.findItem(R.id.action_search)
        }
        val searchView: android.support.v7.widget.SearchView?
        searchView = searchItem.actionView as android.support.v7.widget.SearchView?
        searchView?.queryHint = getString(R.string.search_match)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                query = p0.toString()
                presenter.getSearchMatch(query)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}