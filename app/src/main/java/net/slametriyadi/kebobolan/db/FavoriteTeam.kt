package net.slametriyadi.kebobolan.db

data class FavoriteTeam (
    val id: Long?, val teamId: String?, val strTeam: String?,
    val intFormedYear: String?, val strLeague: String?, val strManager: String?,
    val strStadium: String?, val imgTeam: String?
) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_LEAGUE: String = "TEAM_LEAGUE"
        const val TEAM_MANAGER: String = "TEAM_MANAGER"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
    }
}