package net.slametriyadi.kebobolan.teams.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import net.slametriyadi.kebobolan.teams.detail.fragments.OverviewFragment
import net.slametriyadi.kebobolan.teams.detail.fragments.PlayersFragment

class TeamPagerAdapter(fm: FragmentManager?, val nameTeam: String?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> {
                val f: OverviewFragment = OverviewFragment()
                val args = Bundle()
                args.putString("nameTeam", nameTeam)
                f.arguments = args
                f
            }
            1 -> {
                val a: PlayersFragment = PlayersFragment()
                val args = Bundle()
                args.putString("nameTeam", nameTeam)
                a.arguments = args
                a
            }

            else -> {
                return null
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Overview"
            else -> {
                return "Players"
            }
        }
    }

}