package net.slametriyadi.kebobolan.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite_Data.db", null, 1) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.createTable(
            FavoriteMatch.TABLE_MATCH_FAVORITE, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
            FavoriteMatch.EVENT_NAME to TEXT,
            FavoriteMatch.EVENT_DATE to TEXT,
            FavoriteMatch.HOME_NAME to TEXT,
            FavoriteMatch.AWAY_NAME to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT
        )

        p0.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_FORMED_YEAR to TEXT,
            FavoriteTeam.TEAM_LEAGUE to TEXT,
            FavoriteTeam.TEAM_MANAGER to TEXT,
            FavoriteTeam.TEAM_STADIUM to TEXT,
            FavoriteTeam.TEAM_IMAGE to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.dropTable(FavoriteMatch.TABLE_MATCH_FAVORITE, true)
        p0.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null)
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            return instance as MyDatabaseOpenHelper
        }
    }
}

val Context.database: MyDatabaseOpenHelper get() = MyDatabaseOpenHelper.getInstance(applicationContext)