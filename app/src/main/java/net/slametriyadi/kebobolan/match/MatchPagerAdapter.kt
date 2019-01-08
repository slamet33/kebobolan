package net.slametriyadi.kebobolan.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import net.slametriyadi.kebobolan.match.last.LastFragment
import net.slametriyadi.kebobolan.match.next.NextFragment

class MatchPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when(p0) {
            0 -> {
                LastFragment()
            }
            else -> {
                return NextFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Last Match"
            else -> {
                return "Next Match"
            }
        }
    }

}