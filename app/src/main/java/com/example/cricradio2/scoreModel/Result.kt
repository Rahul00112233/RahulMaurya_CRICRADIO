package com.example.cricradio.scoreModel

import kotlinx.serialization.Serializable

@Serializable
data class ScoreResult(
    val requestParams: RequestParams,
    val responseData: ResponseData,
    val statusCode: Int,
    val time: String
)