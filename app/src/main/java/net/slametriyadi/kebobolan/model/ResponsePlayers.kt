package net.slametriyadi.kebobolan.model

import com.google.gson.annotations.SerializedName

data class ResponsePlayers(
    @field:SerializedName("player")
    val player: List<PlayerItem>
)