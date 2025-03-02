package com.example.cricradio.venueModel

import kotlinx.serialization.Serializable

@Serializable
data class VenueResult(
    val requestParams: RequestParams,
    val responseData: ResponseData,
    val statusCode: Int,
    val time: String
)