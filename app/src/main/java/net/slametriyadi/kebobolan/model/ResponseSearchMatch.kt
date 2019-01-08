package net.slametriyadi.kebobolan.model

import com.google.gson.annotations.SerializedName

data class ResponseSearchMatch(

    @field:SerializedName("event")
    val event: List<EventItem>?
)