package net.slametriyadi.kebobolan.db

data class FavoriteMatch(
    val id: Long?, val eventId: String?, val strEvent: String?,
    val strDate: String?, val homeName: String?, val awayName: String?,
    val homeScore: String?, val awayScore: String?
) {
    companion object {
        const val TABLE_MATCH_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}