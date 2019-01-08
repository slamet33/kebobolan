package net.slametriyadi.kebobolan.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.teams.TeamUI.Companion.team_badge
import net.slametriyadi.kebobolan.teams.TeamUI.Companion.team_name
import net.slametriyadi.kebobolan.teams.detail.DetailTeamActivity
import org.jetbrains.anko.*

class TeamAdapter(private val teams: List<TeamsItem>?, var context: Context?) :
    RecyclerView.Adapter<MyTeamViewHolder>() {

    var size: Int = 0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyTeamViewHolder {
        return MyTeamViewHolder(TeamUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int {
        if (teams != null) {
            size = teams.size
        }
        return size
    }

    override fun onBindViewHolder(p0: MyTeamViewHolder, p1: Int) {
        context?.let { p0.bindItem(teams?.get(p1), it) }
    }

}

class MyTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(teams: TeamsItem?, context: Context) {
        Glide.with(context).load(teams?.strTeamBadge).into(teamBadge)
        teamName.text = teams?.strTeam
        val onClick = itemView.setOnClickListener {
            context.startActivity<DetailTeamActivity>(
                "nameTeam" to teams?.strTeam
            )
        }
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    companion object {
        val team_badge = 6
        val team_name = 7
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
