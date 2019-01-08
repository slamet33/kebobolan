package net.slametriyadi.kebobolan.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.db.FavoriteTeam
import net.slametriyadi.kebobolan.teams.MyTeamViewHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteTeamAdapter(val context: Context?, private val favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolders>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteViewHolders {
        return FavoriteViewHolders(TeamUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onBindViewHolder(p0: FavoriteViewHolders, p1: Int) {
        if (context != null) {
            p0.bindItem(context,favoriteTeam[p1], listener)
        }
    }

}

class FavoriteViewHolders (view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(net.slametriyadi.kebobolan.favorite.TeamUI.team_badge)
    private val teamName: TextView = view.find(net.slametriyadi.kebobolan.favorite.TeamUI.team_name)

    fun bindItem(context: Context, favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Glide.with(context).load(favoriteTeam.imgTeam).into(teamBadge)
        teamName.text = favoriteTeam.strTeam
        itemView.onClick{
            listener(favoriteTeam)
        }
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    companion object {
        val team_badge = 8
        val team_name = 9
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }

            }
        }
    }

}