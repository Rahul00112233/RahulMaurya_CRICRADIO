package com.example.cricradio.venueModel

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val key: String,
    val name: String
)