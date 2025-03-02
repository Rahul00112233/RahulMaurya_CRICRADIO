package com.example.cricradio.venueModel

import kotlinx.serialization.Serializable

@Serializable
data class StartDate(
    val iso: String,
    val sky_check_ts: Int,
    val str: String,
    val timestamp: Int
)