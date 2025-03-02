package com.example.cricradio.scoreModel

import kotlinx.serialization.Serializable

@Serializable
data class LastCommentary(
    val isDone: Boolean,
    val primaryText: String,
    val secondaryText: String?,
    val tertiaryText: String?
)