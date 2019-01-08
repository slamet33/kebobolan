package net.slametriyadi.kebobolan.favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.R.id.*
import net.slametriyadi.kebobolan.db.FavoriteMatch
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteMatchAdapter(private val favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.main_match, parent, false)
        return FavoriteViewHolder(v)
    }

    override fun getItemCount(): Int {
        return favoriteMatch.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txt_HomeTeam: TextView = view.find(txtHomeTeam)
    private val txt_AwayTeam: TextView = view.find(txtAwayTeam)
    private val txt_HomeScore: TextView = view.find(txtHomeScore)
    private val txt_AwayScore: TextView = view.find(txtAwayScore)
    private val txt_Date: TextView = view.find(txtDate)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        txt_HomeTeam.text = favoriteMatch.homeName
        txt_AwayTeam.text = favoriteMatch.awayName
        txt_HomeScore.text = favoriteMatch.homeScore
        txt_AwayScore.text = favoriteMatch.awayScore
        txt_Date.text = favoriteMatch.strDate

        itemView.onClick {
            listener(favoriteMatch)
        }
    }

}
