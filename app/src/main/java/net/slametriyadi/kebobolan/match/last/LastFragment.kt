package net.slametriyadi.kebobolan.match.last

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.R.color.colorAccent
import net.slametriyadi.kebobolan.model.MatchItem
import net.slametriyadi.kebobolan.repository.MatchRepository
import net.slametriyadi.kebobolan.utils.invisible
import net.slametriyadi.kebobolan.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LastFragment : Fragment(), LastView {

    private var teams: MutableList<MatchItem> = mutableListOf()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    var presenter: LastPresenter? = null
    private lateinit var adapter: LastAdapter
    private lateinit var leagueName: String

    companion object {
        var list_last_event = 1
        var spinner_last_event = 2
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                spinner = spinner {
                    id = spinner_last_event
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
                            id = list_last_event
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

            leagueName = "4328"

            val spinnerItems = resources.getStringArray(R.array.league)
            val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
            spinner.adapter = spinnerAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    when (position) {
                        0 -> leagueName = "4328"
                        1 -> leagueName = "4329"
                        2 -> leagueName = "4331"
                        3 -> leagueName = "4332"
                        4 -> leagueName = "4334"
                        5 -> leagueName = "4335"
                    }
                    presenter?.getLastMatch(leagueName.toInt())
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

            swipeRefresh.onRefresh {
                presenter?.getLastMatch(leagueName.toInt())
            }

            presenter = LastPresenter(this@LastFragment, MatchRepository())
            onAttachView()

        }.view
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

    override fun showLastMatch(data: List<MatchItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter = LastAdapter(data, context)
        listTeam.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onAttachView() {
        presenter?.onAttach(this@LastFragment)
        presenter?.getLastMatch(leagueName.toInt())
    }

    override fun onDettachView() {
        presenter?.onDettach()
    }
}