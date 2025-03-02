package com.example.cricradio.scoreModel

import kotlinx.serialization.Serializable

@Serializable
data class ScoreCardResult(
    val announcement1: String,
    val announcement2: String? = null,
    val currentBattingOrder: Int,
    val format: String,
    val key: String,
    val lastCommentary: LastCommentary,
    val now: Now,
    val powerplay: String,
    val powerplayOver: Int,
    val settingObj: SettingObj,
    val status: String,
    val teams: Teams
)

@Serializable
data class Teams(
    val a: A,
    val b: B
)

@Serializable
data class A(
    val a_1_score: A1Score,
    val a_2_score: A1Score,
    val logo: String,
    val name: String,
    val shortName: String
)
@Serializable
data class B(
    val b_1_score: B1Score,
    val b_2_score: B1Score,
    val logo: String,
    val name: String,
    val shortName: String
)