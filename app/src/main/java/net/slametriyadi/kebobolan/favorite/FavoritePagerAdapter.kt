package net.slametriyadi.kebobolan.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import net.slametriyadi.kebobolan.favorite.match.MatchFavoriteFragment
import net.slametriyadi.kebobolan.favorite.teams.TeamsFavoriteFragment

class FavoritePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when(p0) {
            0 -> {
                TeamsFavoriteFragment()
            }
            else -> {
                return MatchFavoriteFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Teams FavoriteMatch"
            else -> {
                return "Match FavoriteMatch"
            }
        }
    }

}