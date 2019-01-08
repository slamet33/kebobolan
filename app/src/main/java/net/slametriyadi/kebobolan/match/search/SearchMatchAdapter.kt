package net.slametriyadi.kebobolan.match.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.main_match.view.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.EventItem
import net.slametriyadi.kebobolan.utils.DateHelper
import org.jetbrains.anko.startActivity

class SearchViewAdapter(private val teams: List<EventItem>?, var context: Context?) :
    RecyclerView.Adapter<TeamViewHolder>() {

    var data : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.main_match, parent, false)
        return TeamViewHolder(v)
    }

    override fun getItemCount(): Int {
        if (teams != null) {
            data = teams.size
        }
        return data
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        teams?.get(position)?.let { holder.bind(it, context) }
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val homeTeamName: TextView? = view.txtHomeTeam
    private val awayTeamName: TextView? = view.txtAwayTeam
    private var homeTeamScore: TextView? = view.txtHomeScore
    private var awayTeamScore: TextView? = view.txtAwayScore
    private val dateMatch: TextView? = view.txtDate
    private val timeMatch: TextView? = view.txtTime


    fun bind(dataMatch: EventItem, context: Context?) {
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
        dateMatch?.text = DateHelper.parseDate(
            dataMatch.dateEvent.toString(),
            DateHelper.inputDateFormat(),
            DateHelper.outputDateFormat()
        )
        timeMatch?.text = DateHelper.parseDate(
            dataMatch.strTime.toString(),
            DateHelper.inputTimeFormat,
            DateHelper.outputTimeFormat
        )
        val onClick = itemView.setOnClickListener {
            context?.startActivity<net.slametriyadi.kebobolan.match.detail.DetailMatchActivity>(
                "idEvent" to dataMatch.idEvent
            )
        }
    }
}