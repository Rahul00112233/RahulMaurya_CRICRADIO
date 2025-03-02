package com.example.cricradio.scoreModel

import kotlinx.serialization.Serializable

@Serializable
data class B1Score(
    val declare: Boolean,
    val overs: String,
    val runs: Int,
    val wickets: Int
)