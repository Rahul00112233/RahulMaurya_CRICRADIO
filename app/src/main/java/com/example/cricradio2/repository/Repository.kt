package com.example.cricradio.repository

import com.example.cricradio.scoreModel.ScoreCardResult
import com.example.cricradio.scoreModel.ScoreResult
import com.example.cricradio.venueModel.VenueResult
import io.ktor.client.HttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val apiService: ApiService) {

    suspend fun getScoreCard(key:String): ScoreResult {
        return apiService.getScoreCard(key)
    }

    suspend fun getVenueCard(key:String):VenueResult{
        return apiService.getVenueCard(key)
    }

}