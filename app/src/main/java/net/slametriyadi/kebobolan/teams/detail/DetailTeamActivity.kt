package net.slametriyadi.kebobolan.teams.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_team.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.db.FavoriteTeam
import net.slametriyadi.kebobolan.db.database
import net.slametriyadi.kebobolan.match.detail.DetailMatchActivity
import net.slametriyadi.kebobolan.model.TeamsItem
import net.slametriyadi.kebobolan.repository.detail.DetailRepository
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var nameTeam: String
    private lateinit var detailPresenter: DetailTeamPresenter
    private var teams: MutableList<TeamsItem> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    lateinit var pagerAdapter: TeamPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbar)

        nameTeam = intent.getStringExtra("nameTeam")
        pagerAdapter = TeamPagerAdapter(supportFragmentManager, nameTeam)
        containerDetailTeam.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()
        tabsDetailTeam.setupWithViewPager(containerDetailTeam)
        favoriteState()
    }

    override fun onStart() {
        super.onStart()
        detailPresenter = DetailTeamPresenter(this, DetailRepository())
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun toastError(msg: String) {
        toast(msg)
    }

    override fun showTeam(dataTeam: List<TeamsItem>?) {
        teams.clear()
        if (dataTeam != null) {
            teams.addAll(dataTeam)
        }

        val data = dataTeam?.get(0)
        Glide.with(applicationContext).load(data?.strTeamBadge).into(detailImgTeam)
        detailNameTeam.text = data?.strTeam
        detailStadiumTeam.text = data?.strStadium
        detailBornYearTeam.text = data?.intFormedYear.toString()
    }

    override fun onAttachView() {
        detailPresenter.onAttach(this)
        detailPresenter.getDataTeam(nameTeam)
    }

    override fun onDettachView() {
        detailPresenter.onDettach()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        DetailMatchActivity::class.java
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs(
                "(TEAM_NAME = {nameTeam})",
                "nameTeam" to nameTeam
            )

            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
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

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teams[0].idTeam,
                    FavoriteTeam.TEAM_NAME to teams[0].strTeam,
                    FavoriteTeam.TEAM_FORMED_YEAR to teams[0].intFormedYear,
                    FavoriteTeam.TEAM_LEAGUE to teams[0].strLeague,
                    FavoriteTeam.TEAM_MANAGER to teams[0].strManager,
                    FavoriteTeam.TEAM_STADIUM to teams[0].strStadium,
                    FavoriteTeam.TEAM_IMAGE to teams[0].strTeamBadge
                )
            }
            toast(database.databaseName)
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeToFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_NAME = {nameTeam})", "nameTeam" to nameTeam)
            }
            toast(getString(R.string.remove))
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }
}
