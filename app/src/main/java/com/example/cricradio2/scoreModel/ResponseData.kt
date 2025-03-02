package com.example.cricradio.scoreModel

import com.example.cricradio.venueModel.VenueResult
import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val message: String,
    val result: ScoreCardResult
)