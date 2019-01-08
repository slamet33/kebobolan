package net.slametriyadi.kebobolan.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.R.color.colorAccent
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.TeamLeagueRepository
import net.slametriyadi.kebobolan.teams.search.SearchTeamActivity
import net.slametriyadi.kebobolan.utils.invisible
import net.slametriyadi.kebobolan.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TeamsFragment : Fragment(), TeamsView {

    private var teams: MutableList<TeamsItem> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    var presenter: TeamsPresenter? = null
    private lateinit var adapter: TeamAdapter
    private lateinit var leagueName: String
    private lateinit var searchItem: MenuItem

    companion object {
        var list_team = 4
        var spinner_team = 5
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner = spinner{
                    id = spinner_team
                }
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listTeam = recyclerView {
                            id = list_team
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

            val spinnerItems = resources.getStringArray(R.array.league)
            val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
            spinner.adapter = spinnerAdapter

            adapter = TeamAdapter(teams, context)
            listTeam.adapter = adapter

            val api = TeamLeagueRepository()
            presenter = TeamsPresenter(this@TeamsFragment, api)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    leagueName = spinner.selectedItem.toString()
                    presenter?.getTeam(leagueName)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

            swipeRefresh.onRefresh {
                presenter?.getTeam(leagueName)
            }

            presenter = TeamsPresenter(this@TeamsFragment, TeamLeagueRepository())
            onAttachView()

        }.view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun hideLoadingError(msg: String) {
        progressBar.invisible()
        toast(msg)
    }

    override fun showTeamLeague(data: List<TeamsItem>?) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        if (data != null) {
            teams.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onAttachView() {
        presenter?.onAttach(this@TeamsFragment)
    }

    override fun onDettachView() {
        presenter?.onDettach()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
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
                this@TeamsFragment.startActivity<SearchTeamActivity>("query" to p0)
                return false
            }
        })
    }
}