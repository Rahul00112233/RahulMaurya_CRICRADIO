package com.example.cricradio.venueModel

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val message: String,
    val result: VenueCardResult
)