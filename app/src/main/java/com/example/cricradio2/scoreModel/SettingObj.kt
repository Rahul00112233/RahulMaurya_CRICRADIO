package com.example.cricradio.scoreModel

import kotlinx.serialization.Serializable

@Serializable
data class SettingObj(
    val currentInning: Int,
    val currentTeam: String
)