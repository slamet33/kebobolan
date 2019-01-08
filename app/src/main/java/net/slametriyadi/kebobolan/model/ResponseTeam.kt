package net.slametriyadi.kebobolan.model

import com.google.gson.annotations.SerializedName
import net.slametriyadi.kebobolan.model.TeamsItem

data class ResponseTeam(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>?
)