package com.example.cricradio.venueModel

import kotlinx.serialization.Serializable

@Serializable
data class B(
    val logo: String,
    val name: String,
    val shortName: String
)