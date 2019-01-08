package net.slametriyadi.kebobolan.model

import com.google.gson.annotations.SerializedName

data class ResponseMatch(

    @field:SerializedName("events")
    val events: List<MatchItem>
)