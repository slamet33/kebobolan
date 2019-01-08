package net.slametriyadi.kebobolan.match.detail

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_detail.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.R.drawable.*
import net.slametriyadi.kebobolan.R.id.add_to_favorite
import net.slametriyadi.kebobolan.R.menu.detail_menu
import net.slametriyadi.kebobolan.db.FavoriteMatch
import net.slametriyadi.kebobolan.db.database
import net.slametriyadi.kebobolan.model.MatchItem
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.inputDateFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.inputTimeFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.outputDateFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.outputTimeFormat
import net.slametriyadi.kebobolan.utils.DateHelper.Companion.parseDate
import net.slametriyadi.kebobolan.utils.invisible
import net.slametriyadi.kebobolan.utils.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var idEvent: String
    private lateinit var detailPresenter: DetailMatchPresenter
    private var teams: MutableList<MatchItem> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailPresenter = DetailMatchPresenter(this, DetailRepository())

        idEvent = intent.getStringExtra("idEvent")
        onAttachView()
        favoriteState()
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_MATCH_FAVORITE).whereArgs(
                "(EVENT_ID = {id})",
                "id" to idEvent
            )

            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun hideLoadingError(msg: String) {
        progressBar.invisible()
        toast(msg)
    }

    @SuppressLint("SetTextI18n")
    override fun showTeam(dataMatch: List<MatchItem>) {
        teams.clear()
        teams.addAll(dataMatch)
        val getData = teams[0]

        txtDetailDate.text = parseDate(getData.dateEvent.toString(), inputDateFormat(), outputDateFormat())
        txtDetailTime.text = parseDate(getData.strTime.toString(), inputTimeFormat, outputTimeFormat)
        if (getData.intHomeScore.toString() == ("null") && getData.intAwayScore.toString() == "null") {
            detailScoreHome.text = getString(R.string.strip_str)
            detailScoreAway.text = getString(R.string.strip_str)
        } else {
            detailScoreHome.text = getData.intHomeScore.toString()
            detailScoreAway.text = getData.intAwayScore.toString()
        }
        detailNameAway.text = getData.strAwayTeam
        detailNameHome.text = getData.strHomeTeam
        detailHomeGoals.text = getData.strHomeGoalDetails
        detailAwayGoals.text = getData.strAwayGoalDetails
        detailYellowHome.text = getData.strHomeYellowCards
        detailYellowAway.text = getData.strAwayYellowCards
        detailRedHome.text = getData.strHomeRedCards
        detailRedAway.text = getData.strAwayRedCards
        detailGoalkeeperHome.text = getData.strHomeLineupGoalkeeper
        detailGoalkeeperAway.text = getData.strAwayLineupGoalkeeper
        detailDefenseHome.text = getData.strHomeLineupDefense
        detailDefenseAway.text = getData.strAwayLineupDefense
        detailMidfieldHome.text = getData.strHomeLineupMidfield
        detailMidfieldAway.text = getData.strAwayLineupMidfield
        detailForwardHome.text = getData.strHomeLineupForward
        detailForwardAway.text = getData.strAwayLineupForward
        detailWeek.text = getString(R.string.week) + getString(R.string.space) + getData.intRound
        detailShotsHome.text = getData.intHomeShots
        detailShotsAway.text = getData.intAwayShots
        detailPresenter.getDataHomeTeams(dataMatch[0].strHomeTeam)
        detailPresenter.getDataAwayTeams(dataMatch[0].strAwayTeam)
    }

    override fun showTeamHome(dataTeamsHome: List<TeamsItem>) {
        val getDataHome = dataTeamsHome[0]
        Glide.with(applicationContext).load(getDataHome.strTeamBadge).into(detailImgHome)
    }

    override fun showTeamAway(dataTeamsAway: List<TeamsItem>) {
        val getDataAway = dataTeamsAway[0]
        Glide.with(applicationContext).load(getDataAway.strTeamBadge).into(detailImgAway)
    }

    override fun onAttachView() {
        detailPresenter.onAttach(this)
        detailPresenter.getDataEvent(idEvent)
    }

    override fun onDettachView() {
        detailPresenter.onDettach()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        DetailMatchActivity::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (teams.size == 0) {

                } else {
                    if (isFavorite) removeToFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_MATCH_FAVORITE,
                    FavoriteMatch.EVENT_ID to teams[0].idEvent,
                    FavoriteMatch.EVENT_NAME to teams[0].strEvent,
                    FavoriteMatch.EVENT_DATE to teams[0].dateEvent,
                    FavoriteMatch.HOME_NAME to teams[0].strHomeTeam,
                    FavoriteMatch.AWAY_NAME to teams[0].strAwayTeam,
                    FavoriteMatch.HOME_SCORE to teams[0].intHomeScore,
                    FavoriteMatch.AWAY_SCORE to teams[0].intAwayScore
                )
            }
            toast(getString(R.string.added))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeToFavorite() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_MATCH_FAVORITE, "(EVENT_ID = {id})", "id" to idEvent)
            }
            toast(getString(R.string.remove))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

}
