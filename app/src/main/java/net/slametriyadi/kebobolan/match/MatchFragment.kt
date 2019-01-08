package net.slametriyadi.kebobolan.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.fragment_blank.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.match.search.SearchMatchActivity
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment() {

    lateinit var adapter: MatchPagerAdapter
    private lateinit var searchItem: MenuItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MatchPagerAdapter(activity?.supportFragmentManager)

        viewpager_main.adapter = adapter
        adapter.notifyDataSetChanged()
        tabs_main.setupWithViewPager(viewpager_main)
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
                this@MatchFragment.startActivity<SearchMatchActivity>("query" to p0)
                return false
            }
        })
    }
}