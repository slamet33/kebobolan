package net.slametriyadi.kebobolan.match.next

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.main_match.view.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.MatchItem
import net.slametriyadi.kebobolan.utils.DateHelper
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.inputTimeFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.outputTimeFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.parseDate
import org.jetbrains.anko.startActivity

class NextAdapter(private val teams: List<MatchItem>, var context: Context?) : RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.main_match, parent, false)
        return TeamViewHolder(v)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position], context)
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val homeTeamName: TextView? = view.txtHomeTeam
    private val awayTeamName: TextView? = view.txtAwayTeam
    private var homeTeamScore: TextView? = view.txtHomeScore
    private var awayTeamScore: TextView? = view.txtAwayScore
    private val dateMatch: TextView? = view.txtDate
    private val timeMatch: TextView? = view.txtTime

    fun bind(dataMatch: MatchItem, context: Context?) {
        homeTeamName?.text = dataMatch.strHomeTeam
        awayTeamName?.text = dataMatch.strAwayTeam
        if (dataMatch.intHomeScore.toString().equals("null")
            && dataMatch.intAwayScore.toString().equals("null")
        ) {
            homeTeamScore?.text = "-"
            awayTeamScore?.text = "-"
        } else {
            homeTeamScore?.text = dataMatch.intHomeScore.toString()
            awayTeamScore?.text = dataMatch.intAwayScore.toString()
        }
        dateMatch?.text =
                parseDate(dataMatch.dateEvent.toString(), DateHelper.inputDateFormat(), DateHelper.outputDateFormat())
        timeMatch?.text = parseDate(dataMatch.strTime.toString(), inputTimeFormat, outputTimeFormat)
        val onClick = itemView.setOnClickListener {
            context?.startActivity<net.slametriyadi.kebobolan.match.detail.DetailMatchActivity>(
                "idEvent" to dataMatch.idEvent
            )
        }
    }
}