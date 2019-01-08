package net.slametriyadi.kebobolan.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*
import net.slametriyadi.kebobolan.R

class FavoriteFragment : Fragment() {

    lateinit var adapter : FavoritePagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoritePagerAdapter(activity?.supportFragmentManager)
        viewpager_main.adapter = adapter
        adapter.notifyDataSetChanged()
        tabs_main.setupWithViewPager(viewpager_main)
    }
}