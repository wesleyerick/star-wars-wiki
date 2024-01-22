package com.wesleyerick.podracer.data.model.starships

import com.google.gson.annotations.SerializedName

data class StarshipsList(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results")
    val starshipsList: List<Starship>
)